package com.central.movies.centralmovies.controllers;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.central.movies.centralmovies.dto.MovieDTO;
import com.central.movies.centralmovies.dto.MoviePlatformsDTO;
import com.central.movies.centralmovies.services.MovieService;

@RestController
@RequestMapping("/api")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping("/movies/info")
  public ResponseEntity<MovieDTO> getMovie(@RequestParam(name = "movie") String query)
      throws IOException, InterruptedException {
    ResponseEntity<MovieDTO> response = movieService.getMovieByMovieText(query);

    if (response != null) {
      return response;
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/movies/platforms")
  public ResponseEntity<MoviePlatformsDTO> get(@RequestParam(name = "movie") String query)
      throws IOException, InterruptedException {
    try {
      return movieService.getPlatformsByMovieText(query);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }
}