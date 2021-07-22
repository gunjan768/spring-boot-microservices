package com.gunjan768.microservice.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// If api-gateway related configuration error: https://stackoverflow.com/questions/55130120/spring-cloud-gateway-and-discoveryclient-routes
// Failed ... 3 queries: https://stackoverflow.com/questions/65333590/spring-cloud-api-gateway-routing-not-working

@EnableEurekaClient		// Not required for this annotation but to explicitly specify that it is an Eureka Client
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
