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

  @GetMapping("/movies/{id}")
  public ResponseEntity<ResponseEntity<List<MovieDTO>>> getMovie(@PathVariable String id) throws IOException, InterruptedException {
    ResponseEntity<List<MovieDTO>> movie = movieService.getMovieById(id);

    if (movie != null) {
      return ResponseEntity.ok(movie);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}