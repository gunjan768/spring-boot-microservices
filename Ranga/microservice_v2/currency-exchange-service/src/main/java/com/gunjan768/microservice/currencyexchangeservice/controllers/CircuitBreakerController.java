package com.gunjan768.microservice.currencyexchangeservice.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    // By default @Retry retires 3 attempts. We can configure that using application.properties file. RateLimiter is used for late limiting
    // which is all about limit the rate of requests send per x seconds. Say, in 10 seconds, I'd want to only allow 10000 calls to the url
    // ("sample-api"). Bulkhead tells how many concurrent calls are allowed.

    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name="sample-api")     // To clearly see it's effect, comment out all other annotations (obviously not GetMapping)
    @Bulkhead(name="sample-api")
    @GetMapping("/sample-api")
    public String sampleApi() {

        logger.info("Sample api call received");

		// ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
		// return forEntity.getBody();

        return "sample-api";
    }

    // hardcodedResponse() is the fallback method for sampleApi() and it accepts one argument which is of type Throwable i.e. Exception
    // as Exception class extends Throwable class. You can have different methods for different types of exceptions. In this we have
    // collected an exception irrespective of it's type.
    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
