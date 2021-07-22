package com.example.movie_catalog_service.models;

public class Rating {

    private String movieId;
    private int rating;

    // When you have Java Marshal (JAXB: Java Architecture for Xml Binding) for Java object to XML, we need to provide an empty
    // constructor. The way the Marshal and Unmarshal things work is java first create an instance and then passes a string and populate
    // one by one. So if you don't have an empty , it doesn't have anything to create an instance.
    public Rating() {

    }

    public Rating(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
