package com.example.movie_catalog_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

// As @EnableCircuitBreaker is deprecated, use resilience4j instead (https://github.com/resilience4j/resilience4j)
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard		// See application.properties file.
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

		// We will make a req to movie info api (for each movies), get the details and that the user need to see. We need to create an
		// instance of RestTemplate which is the utility object which lets you make these calls. RestTemplate is used to make calls and
		// these calls are Synchronous. To make Asynchronous call we can use WebClient class (after adding dependency and it comes under
		// the Reactive Programming). To make an asynchronous call we need to make getCatalog() handler asynchronous.

		// HttpComponentsClientHttpRequestFactory will let you create a timeout property and pass it to RestTemplate constructor.

		// Here we are using HttpComponentsClientHttpRequestFactory for setting timeout but it is not the very correct way of doing.
		// So we have used Hystrix.
//		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//		clientHttpRequestFactory.setConnectTimeout(3000);
//
//		return new RestTemplate(clientHttpRequestFactory);

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
