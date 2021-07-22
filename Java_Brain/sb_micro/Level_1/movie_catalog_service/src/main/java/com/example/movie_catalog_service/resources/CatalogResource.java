package com.example.movie_catalog_service.resources;

import com.example.movie_catalog_service.models.CatalogItem;
import com.example.movie_catalog_service.models.Movie;
import com.example.movie_catalog_service.models.Rating;
import com.example.movie_catalog_service.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    @Autowired  // It is a consumer
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // There is an instance of discovery client in your class path and spring creates an instance when spring loads. It is used to get the
    // information about the discovery clients.
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // getForObject() for GET request. Similarly we have postForObject() for POST request and so on for other HTTP method.
        // We made a request to 8083 and ask EurekaServer whats localhost:8083 is and Eureka says it doesn't know as Eureka knows only
        // the name of the services which are registered (application.properties: service name is same as application name). So in place
        // of localhost:8083, use service name.

        // RestTemplate will "ratings-data-service" will detect this as service name, calls Eureka gets the actual host and port and
        // make the subsequence call. It looks like you are just making a single call but all the abstraction is hidden in the library.
        // This is the service discovery and no hard coded URL.
        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);

        assert ratings != null;
        return ratings.getRatings().stream().map(rating -> {

            // getForObject() takes two arguments: URl you wanna call and the class you need to unmarshal to. So single object is making
            // a call to endpoint and unmarshalling it.

            // For each movie ID, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

            assert movie != null;
            // System.out.println(movie.getMovieId());

            // Put them all together
            return new CatalogItem(movie.getName(), "Test", rating.getRating());

        }).collect(Collectors.toList());
    }


    // This method is a copy of above method but for sending request to endpoints we used WebClient here.
//    @RequestMapping("/{userId}")
//    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
//
//
//        // When you have Java Marshal (JAXB: Java Architecture for Xml Binding) for Java object to XML, we need to provide an empty
//        // constructor. The way the Marshal and Unmarshal things work is java first create an instance and then passes a string and populate
//        // one by one. So if you don't have an empty , it doesn't have anything to create an instance.
//
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 5)
//        );
//
//        // For each movie ID, call movie info service and get details
//        return ratings.stream().map(rating -> {
//
//            Movie movie = webClientBuilder.build()
//                    .get()  // for GET request
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                    .retrieve()     // For retrieving the data
//
//                    // bodyToMono(): Whatever body you get back convert into an instance of this Movie class. It is equivalent to second
//                    // argument of restTemplate.getForObject() method. Mono is a Reactive way of saying you will an object back but not
//                    // right away, you will get sometime in the future (asynchronous like promise in JS)
//                    .bodyToMono(Movie.class)
//                    // Block will again convert the whole code from asynchronous to synchronous as we are blocking the rest of the codes.
//                    // We can avoid blocking by returning the asynchronous object back from this getCatalog() controller handler (method)
//                    // (similar to async await in JS). Asynchronous object like Mono or Flex object.
//                    .block();
//
//            assert movie != null;
//            return new CatalogItem(movie.getName(), "Test", rating.getRating());
//
//        }).collect(Collectors.toList());
//
//        // Put them all together
//    }
}