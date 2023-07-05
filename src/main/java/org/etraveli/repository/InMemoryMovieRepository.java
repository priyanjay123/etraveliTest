package org.etraveli.repository;

import org.etraveli.model.Movie;

import java.util.HashMap;
import java.util.Map;

public class InMemoryMovieRepository implements MovieRepository {
    private Map<String, Movie> movies;

    public InMemoryMovieRepository() {
        movies = new HashMap<>();
        movies.put("F001", Movie.builder().title("You've Got Mail").code("regular").build());
        movies.put("F002", Movie.builder().title("Matrix").code("regular").build());
        movies.put("F003", Movie.builder().title("Cars").code("childrens").build());
        movies.put("F004", Movie.builder().title("Fast & Furious X").code("new").build());
    }

    @Override
    public Movie getMovieById(String movieId) {
        return movies.get(movieId);
    }
}