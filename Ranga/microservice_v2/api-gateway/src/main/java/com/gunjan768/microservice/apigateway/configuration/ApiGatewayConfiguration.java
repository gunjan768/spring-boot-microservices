package com.gunjan768.microservice.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// How to match is called a Predicate. We can match routes on any request attribute like .path(), .method(), .host() etc. filter() is used once
// the request is matched.
@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route(p -> p
                        // If request comes "/get", then we will redirect to URL mentioned in uri() method
                        .path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))  // Will be redirected to this url

                // Any request comes to .path() will be redirected to .uri(). In .uir() url is "lb://currency-exchange" where lb means
                // load balancing and "currency-exchange" is the same name registered with Eureka Server (Naming server). So we are
                // redirecting any request starts with "...../currency-exchange/" to Eureka Server and Eureka will find it's address
                // and find that service and do the load balancing.
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))
                .route(p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion"))
                .build();
    }
}
