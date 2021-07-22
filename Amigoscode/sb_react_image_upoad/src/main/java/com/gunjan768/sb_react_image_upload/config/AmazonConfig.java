package com.gunjan768.sb_react_image_upload.config;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.cloud.aws.core.region.RegionProvider;
import org.springframework.cloud.aws.core.region.StaticRegionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public RegionProvider regionProvider() {
        return new StaticRegionProvider("us-east-2");
    }

    @Bean
    public AmazonS3 getAmazonS3() {

        try {
             AWSCredentials awsCredentials = new BasicAWSCredentials(
                    "AKIAV6XWXK5QCQJ5TRLW",
                    "d0bzMcnYgin2BIZAROznU5i0uifvk4V8w/ptrCJh"
             );

            return AmazonS3ClientBuilder
                    .standard()
                    .withRegion("us-east-2")
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();

        } catch (SdkClientException e) {
            throw new SdkClientException("Failed to connect to AWS");
        }
    }
}