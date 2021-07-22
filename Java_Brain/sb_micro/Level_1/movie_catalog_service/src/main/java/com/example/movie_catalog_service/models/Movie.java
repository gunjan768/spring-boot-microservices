package com.example.movie_catalog_service.models;

public class Movie {
    private String movieId;
    private String name;
    private String description;

    // When you have Java Marshal (JAXB: Java Architecture for Xml Binding) for Java object to XML, we need to provide an empty
    // constructor. The way the Marshal and Unmarshal things work is java first create an instance and then passes a string and populate
    // one by one. So if you don't have an empty , it doesn't have anything to create an instance.
    public Movie() {

    }

    public Movie(String movieId, String name, String description) {
        this.movieId = movieId;
        this.name = name;
        this.description = description;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
