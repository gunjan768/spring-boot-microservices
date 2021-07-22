package com.example.movie_catalog_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {

	// @Bean annotation is the producer. Basically there are many ways to create a bean in SB like using @Component, @Bean etc. @Bean
	// is only used with the method. It says that executes the method and save the instance returned from the method and whoever ask
	// for this instance (of course @Autowired) gives to them. Works very similar to @Component. @Bean works on Singleton concept.

	// @LoadBalanced does the service discovery in the loadBalanced way. It tells RestTemplate that whatever URL is given is not the
	// original URL and just a hint about what service you need to discover. @LoadBalanced is not only doing service discovery but also
	// client side load balancing.
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {

		// We will make a class to movie info api (for each movies), get the details and that the user need to see. We need to create an
		// instance of RestTemplate which is the utility object which lets you make these calls. RestTemplate is used to make calls and
		// these calls are Synchronous. To make Asynchronous call we can use WebClient class (after adding dependency and it comes under
		// the Reactive Programming). To make an asynchronous call we need to make getCatalog() handler asynchronous.
		return new RestTemplate();
	}

	@Bean
	public WebClient.Builder getWebClientBuilder () {

		// WebClient is in Reactive programming space of SB. Reactive deals with the flex object or mono object. Reactive is basically
		// used for asynchronous code.
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
