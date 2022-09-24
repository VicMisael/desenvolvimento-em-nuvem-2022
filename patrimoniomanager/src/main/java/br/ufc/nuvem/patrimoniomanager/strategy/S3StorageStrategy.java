package br.ufc.nuvem.patrimoniomanager.strategy;


import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.File;

@Component
public class S3StorageStrategy implements StorageStrategy{

    private final String S3BucketName;
    private final AmazonS3 s3;

    public S3StorageStrategy(@Value("${patrimoniomanager.bucketname}") String s3BucketName) {
        this.S3BucketName = s3BucketName;
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider("default").getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (C:\\Users\\Misael\\.aws\\credentials), and is in valid format.",
                    e);
        }
        s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("us-east-1")
                .build();
        if (s3.listBuckets().stream().noneMatch(p -> p.getName().equals(s3BucketName))) {
            s3.createBucket(s3BucketName);
        }
    }

    @Override
    public String insertFileAtFolder(String foldername, File file) {
        //Realiza a inserção do arquivo e retorna a url para salvamento no banco
        try {
            s3.putObject(new PutObjectRequest(S3BucketName ,foldername + "/" + file.getName(), file ));
            return getObjectReference(foldername + "/" + file.getName());
        }catch (Exception ex){
            ex.printStackTrace();
            return "";
        }
    }

    @Override
    public boolean deleteFile(String filename) {
        String urlReference = getObjectReference(filename);
        //Se o arquivo existe no folder, realiza a exclusão
        if (!urlReference.equals("")){
            s3.deleteObject(S3BucketName, filename);
            return true;
        }else {

            return false;
        }
    }
    @Override
    public String getObjectReference(String filename){
        //Obtem a lista de arquivos do folder especificado
        ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
                .withBucketName(S3BucketName)
                .withPrefix(filename.split("/")[0]));

        //Verifica se o arquivo em questao está presente no folder

        for (S3ObjectSummary s3Summary:objectListing.getObjectSummaries())
        {
             if (s3Summary.getKey().equals(filename)){
                //Caso o folder possua o arquivo, é retornada a url.
                return s3.getUrl(S3BucketName,filename).toString();
            }
        }
        //Caso o arquivo nao seja encontrado, retorna branco
        return "";





    }

}
