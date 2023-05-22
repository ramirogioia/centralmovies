package com.central.movies.centralmovies.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.central.movies.centralmovies.dto.MovieDTO;
import com.central.movies.centralmovies.services.MovieService;

@RestController
@RequestMapping("/api")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping("/movies/info/{query}")
  public ResponseEntity<MovieDTO> getMovie(@PathVariable String query) throws IOException, InterruptedException {
    ResponseEntity<MovieDTO> response = movieService.getMovieByMovieText(query);

    if (response != null) {
        return response;
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/movies/platforms/{query}")
  public ResponseEntity<MovieDTO> get(@PathVariable String query) throws IOException, InterruptedException {
    ResponseEntity<MovieDTO> response = movieService.getPlatformsByMovieText(query);

    if (response != null) {
        return response;
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}