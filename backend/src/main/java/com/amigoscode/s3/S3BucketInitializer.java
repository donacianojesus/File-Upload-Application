package com.amigoscode.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class S3BucketInitializer implements CommandLineRunner {

    private final S3Service s3Service;

    @Autowired
    public S3BucketInitializer(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @Override
    public void run(String... args) throws Exception {
        // This will run when the application starts
        System.out.println("=== S3 Bucket Initialization ===");
        
        String bucketName = "my-test-bucket-" + System.currentTimeMillis();
        System.out.println("Attempting to create bucket: " + bucketName);
        
        boolean success = s3Service.createBucket(bucketName);
        
        if (success) {
            System.out.println("✅ Bucket created successfully: " + bucketName);
        } else {
            System.out.println("❌ Failed to create bucket: " + bucketName);
        }
        
        System.out.println("=== S3 Initialization Complete ===");
    }
}
