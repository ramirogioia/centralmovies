package com.central.movies.centralmovies.repositories;

import com.central.movies.centralmovies.dto.MoviesPlatforms;
import com.central.movies.centralmovies.dto.MoviesPlatformId;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesPlatformsRepository extends JpaRepository<MoviesPlatforms, MoviesPlatformId> {

    Optional<MoviesPlatforms> findById(Long movie_id);

    List<MoviesPlatforms> findAllById(Long movie_id);
}
