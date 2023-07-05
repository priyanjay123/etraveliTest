package org.etraveli.repository;

import org.etraveli.model.Movie;

public interface MovieRepository {
    Movie getMovieById(String movieId);
}
