package bookmyshow.services;

import bookmyshow.models.Movie;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class MovieService {

    private final Map<String, Movie> movieDB = new HashMap<>();

    public Movie addMovie(Movie movie) {
        movieDB.put(movie.getMovieId(), movie);
        return movie;
    }

    public Movie getMovie(String movieId) {
        return movieDB.get(movieId);
    }

    public Collection<Movie> getAllMovies() {
        return movieDB.values();
    }
}
