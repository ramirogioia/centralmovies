package com.central.movies.centralmovies.services;

import java.io.IOException;
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
import com.central.movies.centralmovies.dto.MoviesPlatforms;
import com.central.movies.centralmovies.dto.SearchCacheEntity;
import com.central.movies.centralmovies.external.ImdbMoviesClient;
import com.central.movies.centralmovies.repositories.MovieRepository;
import com.central.movies.centralmovies.repositories.MoviesPlatformsRepository;
import com.central.movies.centralmovies.repositories.SearchCacheRepository;
import com.central.movies.centralmovies.scraping.WebDriverManagerHead;

@Service
public class MovieService {

    @Autowired
    ImdbMoviesClient imdbClient;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    SearchCacheRepository cacheRepository;

    @Autowired
    MoviesPlatformsRepository platformsRepository;

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
                cacheRepository.save(SearchCacheEntity.builder().query(searchText).movie_id(existingMovie.getMovieId()).build());
                    return ResponseEntity.ok(existingMovie);
            }else{
            movieRepository.save(bestMatch);
            cacheRepository.save(SearchCacheEntity.builder().query(searchText).movie_id(bestMatch.getMovieId()).build());
                    return ResponseEntity.ok(bestMatch);
            }   
            
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }

    public ResponseEntity<List<MoviesPlatforms>> getPlatformsByMovieText(String searchText) throws IOException, InterruptedException {
            MovieDTO cachedMovie = validateAndGetFromCache(searchText);
            
            if (cachedMovie != null) {
                List<MoviesPlatforms> moviePlatformsList = platformsRepository.findAllById(cachedMovie.getMovieId());
                if (!moviePlatformsList.isEmpty()) {
                    //run automation here to add information
                    //save all information automation + name from DB MovieDTO
                    return null;
                } 
                else {
                    return ResponseEntity.ok(moviePlatformsList);
                }
            }
            else{
                ResponseEntity<MoviesListDTO> response = imdbClient.getImdbMovie(formatSearchText(searchText));
                if (response.getStatusCode() == HttpStatus.OK) {
                    MovieDTO bestMatch = this.getBestMatchFromResultListBasedOnRanking(response.getBody().getMovies());
                    MovieDTO existingMovie = validateMovieAlreadyExisting(bestMatch.getImdbId());

                    if (existingMovie != null) {
                        cacheRepository.save(SearchCacheEntity.builder().query(searchText).movie_id(existingMovie.getMovieId()).build());
                    }else{
                    movieRepository.save(bestMatch);
                    cacheRepository.save(SearchCacheEntity.builder().query(searchText).movie_id(bestMatch.getMovieId()).build());
                    }   
                    
                } else {
                    return ResponseEntity.status(response.getStatusCode()).body(null);
                }
                //run automation here to add information
                //save all information automation + name from DB MovieDTO
                return null;
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

    private String formatID(String id) {
        return id.replaceAll("[a-zA-Z]", "");
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

    public ResponseEntity<List<String>> getListOfPlatforms(String searchText){
        driverManager = new WebDriverManagerHead(false);
        List<String> availablePlatforms = driverManager.getListOfPlatforms(searchText, true);

        //availablePlatforms = availablePlatforms.stream().map(s -> s.toUpperCase().replaceAll(" ", "_"))
        //.collect(Collectors.toList());

        return ResponseEntity.ok(availablePlatforms); 
    }

}

