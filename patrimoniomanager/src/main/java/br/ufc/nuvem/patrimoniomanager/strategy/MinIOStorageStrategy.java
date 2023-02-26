package br.ufc.nuvem.patrimoniomanager.strategy;

import br.ufc.nuvem.patrimoniomanager.model.util.FileData;
import br.ufc.nuvem.patrimoniomanager.model.util.FileReference;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.actions.S3Actions;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.AwsHostNameUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
@Slf4j
public final class MinIOStorageStrategy implements StorageStrategy {


    private final String minioBucketName;
    private final AmazonS3 amazonS3interface;

    public MinIOStorageStrategy(@Value("${patrimoniomanager.bucketname}") String minioBucketName,
                                @Value("${patrimoniomanager.minio.accesskey}") String minioAccessKey,
                                @Value("${patrimoniomanager.minio.secretkey}") String minioSecretKey,
                                @Value("${patrimoniomanager.connection.string}") String minioConnection) {
        log.info("Using Minio");
        this.minioBucketName = minioBucketName;
        AWSCredentials credentials = null;
        try {
            credentials = new BasicAWSCredentials(minioAccessKey, minioSecretKey);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        amazonS3interface = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(minioConnection, AwsHostNameUtils.parseRegion(minioConnection, AmazonS3Client.S3_SERVICE_NAME)))
                .withPathStyleAccessEnabled(true)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        if (amazonS3interface.listBuckets().stream().noneMatch(p -> p.getName().equals(minioBucketName))) {
            amazonS3interface.createBucket(minioBucketName);
            amazonS3interface.setBucketPolicy(minioBucketName, createPolicyJson(minioBucketName));
        }
    }


    @SneakyThrows
    @Override
    public FileData generateUploadLink(FileReference fileReference) {
        final String filename = fileReference.getFolderName() + "/" + fileReference.getFilename();
        final String presigned = amazonS3interface.generatePresignedUrl(minioBucketName,
                filename,
                Date.from(Instant.now().plus(Duration.ofDays(1)))
                , HttpMethod.PUT).toString();
        return FileData.builder().presignedLink(presigned).filename(filename).build();
    }

    @Override
    public boolean deleteFile(String filename) {
        if (filename != null) {
            String urlReference = getUrl(filename);
            //Se o arquivo existe no folder, realiza a exclusão
            if (!urlReference.equals("")) {
                amazonS3interface.deleteObject(minioBucketName, filename);
                String foldername = filename.split("/")[0];
                if (amazonS3interface.listObjects(minioBucketName, foldername).getObjectSummaries().isEmpty()) {
                    amazonS3interface.deleteObject(minioBucketName, foldername);
                }
                return true;
            } else {

                return false;
            }

        }
        return true;
    }

    @Override
    public String getUrl(String filename) {
        return amazonS3interface.getUrl(minioBucketName, filename).toString().replaceAll("minio", "localhost");

    }

    private static String createPolicyJson(String bucketName) {
        Statement statement = new Statement(Statement.Effect.Allow)
                .withPrincipals(Principal.AllUsers)
                .withActions(S3Actions.GetObject)
                .withResources(
                        new Resource("arn:aws:s3:::" + bucketName + "/*")
                );
        return new Policy(UUID.randomUUID().toString(), List.of(statement)).toJson();
    }
}
