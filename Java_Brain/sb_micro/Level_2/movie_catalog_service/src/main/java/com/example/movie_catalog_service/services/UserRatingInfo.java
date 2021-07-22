package com.example.movie_catalog_service.services;

import com.example.movie_catalog_service.models.Rating;
import com.example.movie_catalog_service.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class UserRatingInfo {

    @Autowired  // It is a consumer
    private RestTemplate restTemplate;

    // Hystrix is a library from Netflix. Hystrix isolates the points of access between the services, stops cascading failures across them and
    // provides the fallback options. For example, when you are calling a 3rd party application, it takes more time to send the response. So
    // at that time, the control goes to the fallback method and returns the custom response to your application.
    @HystrixCommand(
            fallbackMethod = "getFallbackUserRating",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "200"),
                    // look at the last n requests (n = 5)
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    // Out last n requests, if 50% fails
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    // How long the circuit breaker sleeps before it picks up again (here 5 seconds)
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            }
    )
    public UserRating getUserRating(@PathVariable("userId") String userId) {

        // getForObject() for GET request. Similarly we have postForObject() for POST request and so on for other HTTP method.
        // We made a request to 8083 and ask EurekaServer whats localhost:8083 is and Eureka says it doesn't know as Eureka knows only
        // the name of the services which are registered (application.properties: service name is same as application name). So in place
        // of localhost:8083, use service name.

        // RestTemplate will "ratings-data-service" will detect this as service name, calls Eureka gets the actual host and port and
        // make the subsequence call. It looks like you are just making a single call but all the abstraction is hidden in the library.
        // This is the service discovery and no hard coded URL.
        return restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
    }

    public UserRating getFallbackUserRating(@PathVariable String userId) {

        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(Collections.singletonList(new Rating("0", 0)));

        return userRating;
    }
}
