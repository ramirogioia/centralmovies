package com.central.movies.centralmovies.repositories;

import com.central.movies.centralmovies.dto.MoviesPlatform;
import com.central.movies.centralmovies.dto.MoviesPlatformId;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesPlatformsRepository extends JpaRepository<MoviesPlatform, MoviesPlatformId> {

    Optional<MoviesPlatform> findById(MoviesPlatformId id);

    List<MoviesPlatform> findAllById(MoviesPlatformId id);
}
