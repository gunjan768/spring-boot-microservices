package com.example.my_web_app_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring by default returns JSON (as we have Jackson which converts data to JSON). To make Spring boot return XML we need to use maven
// dependency for it which can convert the data to XML.
@SpringBootApplication
public class MyWebApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(MyWebApp2Application.class, args);
	}
}
