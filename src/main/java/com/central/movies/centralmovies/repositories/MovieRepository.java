package com.central.movies.centralmovies.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.central.movies.centralmovies.dto.MovieDTO;

public interface MovieRepository extends JpaRepository<MovieDTO, Long> {
    
    List<MovieDTO> findByName(String name);

    MovieDTO findByMovieId(Long movieId);

    MovieDTO findByImdbId(String imdbId);
    
}