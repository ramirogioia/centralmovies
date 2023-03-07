package com.central.movies.centralmovies.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.central.movies.centralmovies.dto.MovieDTO;
import com.central.movies.centralmovies.external.ImdbMoviesClient;

@Service
public class MovieService {

    @Autowired
    ImdbMoviesClient imdbClient;

    public ResponseEntity<List<MovieDTO>> getMovieById(String searchText) throws IOException, InterruptedException {
        return ResponseEntity.ok(imdbClient.getImdbMovie(searchText).getBody().getMovies());
    }

}
