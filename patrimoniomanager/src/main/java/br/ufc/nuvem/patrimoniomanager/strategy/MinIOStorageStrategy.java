package br.ufc.nuvem.patrimoniomanager.strategy;

import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@Profile("docker")
@Slf4j
public class MinIOStorageStrategy implements StorageStrategy {

    public MinioClient client;


    private long requestsize = 2048;


    @Value("${patrimoniomanager.connection.string}")
    private String urlMinio;


    private final String minioBucketName;

    public MinIOStorageStrategy(@Value("${patrimoniomanager.bucketname}") String minioBucketName,
                                @Value("${patrimoniomanager.minio.accesskey}") String minioAccessKey,
                                @Value("${patrimoniomanager.minio.secretkey}") String minioSecretKey,
                                @Value("${patrimoniomanager.connection.string}") String minioConnection) {
        log.info("using MINIO");
        this.minioBucketName = minioBucketName;
        client = MinioClient.builder().endpoint(minioConnection).credentials(minioAccessKey, minioSecretKey).build();

        try {
            if (!client.bucketExists(BucketExistsArgs.builder().bucket(minioBucketName).build())) {
                client.makeBucket(MakeBucketArgs.builder().bucket(minioBucketName).build());
            }
        } catch (ErrorResponseException | NoSuchAlgorithmException | InsufficientDataException | InternalException |
                 InvalidKeyException | XmlParserException | ServerException | IOException |
                 InvalidResponseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String insertFileAtFolder(String foldername, MultipartFile file) {
        try {
            client.putObject(PutObjectArgs.builder()
                    .bucket(minioBucketName)
                    .object(foldername + "/" + file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), -1).build());
            return foldername + "/" + file.getOriginalFilename();
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("failed upload");
            return "";

        }

    }

    @Override
    public boolean deleteFile(String filename) {
        try {
            client.removeObject(RemoveObjectArgs.builder().bucket(minioBucketName).object(filename).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getUrl(String filename) {
        try {
            return urlMinio + "/" + client.getObject(GetObjectArgs.builder().bucket(minioBucketName).object(filename).build()).object();
        } catch (Exception e) {
            log.error(e.toString());
        }
        return "";
    }
}
