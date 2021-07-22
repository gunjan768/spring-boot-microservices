package com.example.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

// ConfigServer doesn't know where to pull up the values from so add a property in properties file which says for the values use
// Git say GitHub. For the sake of this tutorial we used local Git repo. Access URL: http://localhost:8081/application/default.
@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerApplication.class, args);
	}

}
