package com.example.movie_catalog_service.services;

import com.example.movie_catalog_service.models.CatalogItem;
import com.example.movie_catalog_service.models.Movie;
import com.example.movie_catalog_service.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired  // It is a consumer
    private RestTemplate restTemplate;

    // Hystrix is a library from Netflix. Hystrix isolates the points of access between the services, stops cascading failures across them and
    // provides the fallback options. For example, when you are calling a 3rd party application, it takes more time to send the response. So
    // at that time, the control goes to the fallback method and returns the custom response to your application.
    @HystrixCommand(
            fallbackMethod = "getFallbackCatalogItem"
    )
    public CatalogItem getCatalogItem(Rating rating) {

        // getForObject() takes two arguments: URl you wanna call and the class you need to unmarshal to. So single object is making
        // a call to endpoint and unmarshalling it.
        // For each movie ID, call movie info service and get details
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

        assert movie != null;
        // System.out.println(movie.getMovieId());

        // Put them all together
        return new CatalogItem(movie.getName(), "Test", rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name not found", "", rating.getRating());
    }
}