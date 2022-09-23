package br.ufc.nuvem.patrimoniomanager.strategy;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
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
import org.springframework.stereotype.Component;


import java.io.File;

@Component
public class S3StorageStrategy implements StorageStrategy{
    @Override
    public String createFolder(String foldername) {
        return null;
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
