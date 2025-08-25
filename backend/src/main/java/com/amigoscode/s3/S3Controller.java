package com.amigoscode.s3;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {

    private final S3Service s3Service;

    @Autowired
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/bucket")
    public String createBucket(@RequestParam String bucketName) {
        boolean success = s3Service.createBucket(bucketName);
        if (success) {
            return "Bucket '" + bucketName + "' created successfully!";
        } else {
            return "Failed to create bucket '" + bucketName + "' or it already exists.";
        }
    }

    @GetMapping("/bucket/{bucketName}/exists")
    public String checkBucketExists(@PathVariable String bucketName) {
        try {
            s3Service.getObject(bucketName, "test-key");
            return "Bucket '" + bucketName + "' exists and is accessible.";
        } catch (Exception e) {
            return "Bucket '" + bucketName + "' does not exist or is not accessible: " + e.getMessage();
        }
    }
}
