package com.upnextdev.datagen.aws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {

	@Bean
	public AmazonS3 amazonS3() {
		BasicAWSCredentials creds = new BasicAWSCredentials("AKIAU5MDYRNIYB5LUQWD", "c+TJZfiUsf0/yv3g5UCxrbBzV7X2wnSTbAYLCmqE");
		return AmazonS3ClientBuilder.standard().withRegion("us-east-2")
				.withCredentials(new AWSStaticCredentialsProvider(creds)).build();
	}
}
