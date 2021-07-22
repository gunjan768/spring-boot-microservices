package com.gunjan768.amazon_sqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;

@SpringBootApplication(
        exclude = {ContextStackAutoConfiguration.class}
)
public class AmazonSqsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonSqsApplication.class, args);
    }

}
