package com.example.movie_info_service.resources;

import com.example.movie_info_service.models.Movie;
import com.example.movie_info_service.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        final String URL = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey;

        // System.out.println("getMovieInfo : " + apiKey);
        // System.out.println("URL : " + URL);

        MovieSummary movieSummary = restTemplate.getForObject(URL, MovieSummary.class);

        // System.out.println("movieSummary : " + movieSummary.getTitle() + " " + movieSummary.getOverview());

        assert movieSummary != null;
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}