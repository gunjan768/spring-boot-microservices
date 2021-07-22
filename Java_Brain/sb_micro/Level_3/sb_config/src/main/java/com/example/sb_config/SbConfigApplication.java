package com.example.sb_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Now this app will act as a config client. Now I need to tell where the config server is so that it can connect to it for the global
// configuration values.

// To load microservice specific property from config server, how can we achieve this ? Basically we created one file named "application.yml"
// which serves as a global and common to all microservices. For microservice specific file, we need to create a new file with the name
// same to application name (for this file name = "microservice_1" as name of this app is also "microservice_1").
@SpringBootApplication
public class SbConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbConfigApplication.class, args);
	}

}
