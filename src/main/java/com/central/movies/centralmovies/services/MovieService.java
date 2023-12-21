package com.central.movies.centralmovies.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.central.movies.centralmovies.dto.MovieDTO;
import com.central.movies.centralmovies.dto.MoviesListDTO;
import com.central.movies.centralmovies.dto.MoviesPlatformId;
import com.central.movies.centralmovies.dto.MoviesPlatforms;
import com.central.movies.centralmovies.dto.Platforms;
import com.central.movies.centralmovies.dto.SearchCacheEntity;
import com.central.movies.centralmovies.external.ImdbMoviesClient;
import com.central.movies.centralmovies.repositories.MovieRepository;
import com.central.movies.centralmovies.repositories.MoviesPlatformsRepository;
import com.central.movies.centralmovies.repositories.PlatformsRepository;
import com.central.movies.centralmovies.repositories.SearchCacheRepository;
import com.central.movies.centralmovies.scraping.WebDriverManagerHead;
import com.github.dockerjava.api.exception.NotFoundException;

@Service
public class MovieService {

    @Autowired
    ImdbMoviesClient imdbClient;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    SearchCacheRepository cacheRepository;

    @Autowired
    MoviesPlatformsRepository moviesPlatformsRepository;

    @Autowired
    PlatformsRepository platformsRepository;

    WebDriverManagerHead driverManager;

    public ResponseEntity<MovieDTO> getMovieByMovieText(String searchText) throws IOException, InterruptedException {

        MovieDTO cachedMovie = validateAndGetFromCache(searchText);

        if (cachedMovie != null) {
            return ResponseEntity.ok(cachedMovie);
        }

        ResponseEntity<MoviesListDTO> response = imdbClient.getImdbMovie(formatSearchText(searchText));
        if (response.getStatusCode() == HttpStatus.OK) {
            MovieDTO bestMatch = this.getBestMatchFromResultListBasedOnRanking(response.getBody().getMovies());
            MovieDTO existingMovie = validateMovieAlreadyExisting(bestMatch.getImdbId());

            if (existingMovie != null) {
                cacheRepository.save(
                        SearchCacheEntity.builder().query(searchText).movie_id(existingMovie.getMovieId()).build());
                return ResponseEntity.ok(existingMovie);
            } else {
                movieRepository.save(bestMatch);
                cacheRepository
                        .save(SearchCacheEntity.builder().query(searchText).movie_id(bestMatch.getMovieId()).build());
                return ResponseEntity.ok(bestMatch);
            }

        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }

    public ResponseEntity<List<Platforms>> getPlatformsByMovieText(String searchText)
            throws IOException, InterruptedException, org.openqa.selenium.NoSuchElementException {
        MovieDTO cachedMovie = validateAndGetFromCache(searchText);

        if (cachedMovie != null) {
            List<MoviesPlatforms> moviePlatformsList = moviesPlatformsRepository
                    .findAllByMovieId(cachedMovie.getMovieId());
            if (moviePlatformsList.isEmpty()) {
                Optional<MovieDTO> movie = movieRepository.findById(cachedMovie.getMovieId());

                return getListOfPlatforms(movie.get(), movie.get().getName());
                
            } else {
                List<Platforms> platformsList = moviePlatformsList.stream()
                        .map(MoviesPlatforms::getPlatform)
                        .collect(Collectors.toList());

                return ResponseEntity.ok(platformsList);
            }
        } else {
            ResponseEntity<MoviesListDTO> response = imdbClient.getImdbMovie(formatSearchText(searchText));
            if (response.getStatusCode() == HttpStatus.OK) {
                MovieDTO bestMatch = this.getBestMatchFromResultListBasedOnRanking(response.getBody().getMovies());
                MovieDTO existingMovie = validateMovieAlreadyExisting(bestMatch.getImdbId());

                if (existingMovie != null) {
                    cacheRepository.save(
                            SearchCacheEntity.builder().query(searchText).movie_id(existingMovie.getMovieId()).build());
                            Optional<MovieDTO> movie = movieRepository.findById(existingMovie.getMovieId());

                            return getListOfPlatforms(movie.get(), movie.get().getName());
                } else {
                    movieRepository.save(bestMatch);
                    cacheRepository.save(
                            SearchCacheEntity.builder().query(searchText).movie_id(bestMatch.getMovieId()).build());
                            Optional<MovieDTO> movie = movieRepository.findById(bestMatch.getMovieId());

                            return getListOfPlatforms(movie.get(), movie.get().getName());
                }
            } else {
                return ResponseEntity.status(response.getStatusCode()).body(null);
            }
        }
    }

    private MovieDTO getBestMatchFromResultListBasedOnRanking(List<MovieDTO> list) {
        if (list.size() == 1) {
            return list.get(0);
        } else {
            Optional<MovieDTO> bestMatch = list.stream()
                    .min(Comparator.comparingInt(MovieDTO::getRanking));

            if (bestMatch != null) {
                MovieDTO match = bestMatch.get();
                return match;
            } else {
                return null;
            }
        }
    }

    private MovieDTO validateAndGetFromCache(String query) {
        SearchCacheEntity result = cacheRepository.findByQuery(query);

        if (result != null) {
            MovieDTO cachedMovie = movieRepository.findByMovieId(result.getMovie_id());
            if (cachedMovie != null) {
                return cachedMovie;
            }
        }
        return null;
    }

    private MovieDTO validateMovieAlreadyExisting(String imdbId) {
        MovieDTO result = movieRepository.findByImdbId(imdbId);

        if (result != null) {
            return result;
        } else {
            return null;
        }

    }

    private String formatSearchText(String searchText) {
        return searchText.replace(" ", "%20");
    }

public ResponseEntity<List<Platforms>> getListOfPlatforms(MovieDTO movie, String searchText)
        throws org.openqa.selenium.NoSuchElementException {
    driverManager = new WebDriverManagerHead(false);
    String availablePlatforms = driverManager.getPlatformsInformationFromScrapping(searchText, false);
    List<Platforms> availablePlatformsFromScrapping;

    if (!availablePlatforms.isBlank() && !availablePlatforms.isEmpty()) {

        List<Platforms> availablePlatformsFromDb = platformsRepository.findAll();
        if (availablePlatformsFromDb.size() == 0) {
            throw new NotFoundException("No hay plataformas en la base de datos.");
        }

        availablePlatformsFromScrapping = getAvailablePlatformsFromText(availablePlatformsFromDb,
                availablePlatforms);

        if (!availablePlatformsFromScrapping.isEmpty()) {

            List<MoviesPlatforms> moviesPlatformsList = availablePlatformsFromScrapping.stream()
            .map(platform -> {
                MoviesPlatforms moviesPlatforms = MoviesPlatforms.builder()
                        .id(new MoviesPlatformId(movie.getMovieId(), platform.getPlatformId()))
                        .movie(movie)
                        .platform(platform)
                        .registeredDate(new Timestamp(System.currentTimeMillis()))
                        .build();
                return moviesPlatforms;
            })
            .collect(Collectors.toList());

            moviesPlatformsRepository.saveAll(moviesPlatformsList);
        }

    } else {
        throw new NotFoundException("Elemento no encontrado.");
    }

    return ResponseEntity.ok(availablePlatformsFromScrapping);
}


    private List<Platforms> getAvailablePlatformsFromText(List<Platforms> availablePlatformsFromDb, String availablePlatforms) {
        List<Platforms> matchingPlatforms = new ArrayList<>();
    
        String[] lines = availablePlatforms.split("\n");
        List<String> platformDescriptions = availablePlatformsFromDb.stream()
                .map(platform -> platform.getDescription().toUpperCase())
                .collect(Collectors.toList());
    
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                String lineUpperCase = line.trim().toUpperCase();
                if (platformDescriptions.stream().anyMatch(desc -> desc.contains(lineUpperCase))) {
                    Platforms matchingPlatform = availablePlatformsFromDb.stream()
                            .filter(platform -> platform.getDescription().toUpperCase().contains(lineUpperCase))
                            .findFirst()
                            .orElse(null);
    
                    if (matchingPlatform != null) {
                        matchingPlatforms.add(matchingPlatform);
                    }
                }
            }
        }
        return matchingPlatforms;
    }
}