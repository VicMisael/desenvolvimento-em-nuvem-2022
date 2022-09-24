package br.ufc.nuvem.patrimoniomanager.strategy;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.model.S3Storage;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.File;

@Component
public class S3StorageStrategy implements StorageStrategy {

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
    public boolean insertFileAtFolder(String foldername, File file) {
        return false;
    }

    @Override
    public boolean deleteFile(String filename) {
        return false;
    }

    @Override
    public String getObjectReference(String filename) {
        return null;
    }

    @Override
    public String deleteObject(String object) {
        return null;
    }
}
