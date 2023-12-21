package com.central.movies.centralmovies.repositories;

import com.central.movies.centralmovies.dto.MoviesPlatforms;
import com.central.movies.centralmovies.dto.MoviesPlatformId;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoviesPlatformsRepository extends JpaRepository<MoviesPlatforms, MoviesPlatformId> {

    @Query("SELECT mp FROM movies_platforms mp WHERE mp.id.movie = :movieId")
    List<MoviesPlatforms> findAllByMovieId(@Param("movieId") Long movieId);

    Optional<MoviesPlatforms> findByIdMovieAndIdPlatform(Long movieId, Long platformId);

    @Query("SELECT mp FROM movies_platforms mp WHERE mp.id.platform = :platformId")
    List<MoviesPlatforms> findAllByPlatformId(@Param("platformId") Long platformId);
}