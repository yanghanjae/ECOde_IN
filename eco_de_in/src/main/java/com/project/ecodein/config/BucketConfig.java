package com.project.ecodein.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class BucketConfig {
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String accessSecret;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
            .credentialsProvider(this :: awsCredentials)
            .region(Region.of(region))
            .build();
    }

    private AwsCredentials awsCredentials() {
        return new AwsCredentials() {

            @Override
            public String accessKeyId () {
                return accessKey;
            }

            @Override
            public String secretAccessKey () {
                return accessSecret;
            }
        };
    }
}
