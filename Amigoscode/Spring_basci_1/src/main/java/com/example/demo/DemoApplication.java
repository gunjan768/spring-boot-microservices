package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @Component is a class level annotation. During the component scan, Spring Framework automatically detects classes annotated with @Component.
// By default, the bean instances of this class have the same name as the class name with a lowercase initial. Since @Repository, @Service,
// @Configuration, and @Controller are all meta-annotations of @Component, they share the same bean naming behavior. Also, Spring automatically
// picks them up during the component scanning process.
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}