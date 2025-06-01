package com.upnextdev.datagen.aws.config;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3;

    private final String bucketName = "faux-data-files-test";

    public void uploadFile(String key, MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        s3.putObject(bucketName, key, file.getInputStream(), metadata);
    }

    public URL generatePresignedUrl(String key, HttpMethod method) {
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 10); // 10 min
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key)
                .withMethod(method)
                .withExpiration(expiration);
        return s3.generatePresignedUrl(request);
    }

    public S3Object downloadFile(String key) {
        return s3.getObject(bucketName, key);
    }
}
