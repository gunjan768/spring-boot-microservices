package com.gunjan768.restful.restful_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulDemoApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {

		// This will accept a Locale but you have to accept it as a parameter in the method handler.
		// SessionLocaleResolver localeResolver = new SessionLocaleResolver();

		// If want the Locale using request header "Accept-Language" then use AcceptHeaderLocaleResolver. What this class will do is accept the
		// Locale using that request header and you don't need to accept it as the parameter (see HelloWorld.java class). What you need to do is
		// to use the static method getLocale() from LocaleContextHolder (LocaleContextHolder.getLocale()).
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();

		localeResolver.setDefaultLocale(Locale.US);

		return localeResolver;
	}

	// Since we set the baseName in the application.properties file so we commented out this method
	// @Bean
	// public ResourceBundleMessageSource messageSource() {
	// 	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	// 	messageSource.setBasename("messages");

	// 	return messageSource;
	// }
}