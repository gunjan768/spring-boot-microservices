package com.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://docs.spring.io/spring-security/site/docs/3.2.0.RC2/reference/htmlsingle/#jc (Spring Security)
// https://www.baeldung.com/spring-security-authentication-provider
// https://www.baeldung.com/spring-security-login

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
