package com.amigoscode.s3;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.BucketAlreadyExistsException;
import software.amazon.awssdk.services.s3.model.BucketAlreadyOwnedByYouException;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import java.io.IOException;

@Service
public class S3Service {

    private final S3Client s3Client;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public boolean createBucket(String bucketName) {
        try {
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .build();
            
            CreateBucketResponse response = s3Client.createBucket(createBucketRequest);
            System.out.println("Bucket '" + bucketName + "' created successfully!");
            return true;
        } catch (BucketAlreadyExistsException e) {
            System.out.println("Bucket '" + bucketName + "' already exists.");
            return false;
        } catch (BucketAlreadyOwnedByYouException e) {
            System.out.println("Bucket '" + bucketName + "' already owned by you.");
            return false;
        } catch (Exception e) {
            System.err.println("Error creating bucket '" + bucketName + "': " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void putObject(String bucketName, String key, byte[] file) {
        PutObjectRequest ObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(ObjectRequest, RequestBody.fromBytes(file));
    }

    public byte[] getObject(String bucketName, String key) { 
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
      ResponseInputStream<GetObjectResponse> res = s3Client.getObject(getObjectRequest);

      
      try {
         return res.readAllBytes();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
    }
}
