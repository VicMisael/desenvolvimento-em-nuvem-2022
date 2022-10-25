package br.ufc.nuvem.patrimoniomanager.strategy;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.AwsHostNameUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static br.ufc.nuvem.patrimoniomanager.util.BucketUtils.createPolicyJson;

@Component
@Profile("docker")
@Slf4j

public class MinIOStorageStrategy implements StorageStrategy {

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

    @Override
    public String insertFileAtFolder(String foldername, MultipartFile file) {

        //Realiza a inserção do arquivo e retorna a url para salvamento no banco
        try {
            ObjectMetadata data = new ObjectMetadata();
            data.setContentType(file.getContentType());
            data.setContentLength(file.getSize());

            amazonS3interface.putObject(new PutObjectRequest(minioBucketName, foldername + "/" + file.getOriginalFilename(), file.getInputStream(), data).withCannedAcl(CannedAccessControlList.PublicRead));
            return foldername + "/" + file.getOriginalFilename();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
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
        return amazonS3interface.getUrl(minioBucketName, filename).toString();

    }
}
