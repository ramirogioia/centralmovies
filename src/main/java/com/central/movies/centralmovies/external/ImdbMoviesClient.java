package com.central.movies.centralmovies.external;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.central.movies.centralmovies.dto.MoviesListDTO;
import com.central.movies.centralmovies.utils.GsonHelper;

@Component
public class ImdbMoviesClient {

    private static final String API_URL = "https://imdb8.p.rapidapi.com";
    private static final String imdbApiKey = "084bf068d8mshffe3a96e8cb82dap1317e4jsneffdcb6a0820";
    private static final String imdbApiHost = "imdb8.p.rapidapi.com";


    public ResponseEntity<MoviesListDTO> getImdbMovie(String searchText)
            throws IOException, InterruptedException {

        String url = String.format(API_URL
        + "/auto-complete?q=%s", searchText);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().header("Content-Type", "application/json")
                .header("X-RapidAPI-Key", imdbApiKey).header("X-RapidAPI-Host", imdbApiHost).method("GET", HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        if (response.statusCode() == HttpStatus.OK.value()
                || response.statusCode() == HttpStatus.OK.value()) {            
            MoviesListDTO responsePayload = GsonHelper.customGson.fromJson(response.body(),
            MoviesListDTO.class);
            return ResponseEntity.ok(responsePayload);
        }
        
        return null;
    }

}