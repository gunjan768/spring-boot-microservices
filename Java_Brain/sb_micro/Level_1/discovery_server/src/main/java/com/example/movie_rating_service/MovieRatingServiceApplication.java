package com.example.movie_rating_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// If @EnableEurekaServer doesn't work see below or refer -> https://stackoverflow.com/questions/6642146/maven-failed-to-read-artifact-descriptor
// Had the same issue with IntelliJ IDEA and following worked.
//Go to File -> Select Settings -> Select Build, Execution, Deployments -> Select Build Tools from drop down -> Select Maven from drop down
// -> Tick the Always update snapshots check box

@SpringBootApplication
@EnableEurekaServer
public class MovieRatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRatingServiceApplication.class, args);
	}

}
