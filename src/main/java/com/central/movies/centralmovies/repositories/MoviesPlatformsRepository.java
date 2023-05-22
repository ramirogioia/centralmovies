package com.central.movies.centralmovies.repositories;

import com.central.movies.centralmovies.dto.MoviePlatformEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesPlatformsRepository extends JpaRepository<MoviePlatformEntity, Long> {

    MoviePlatformEntity findByMovieId(Long movieId);

    MoviePlatformEntity findAllByMovieIds(Long movieId);

}