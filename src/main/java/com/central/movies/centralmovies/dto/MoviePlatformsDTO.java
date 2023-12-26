package com.central.movies.centralmovies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MoviePlatformsDTO {

    @JsonProperty("movie_name")
    private String movieName;

    @JsonProperty("platforms")
    private List<Platforms> platforms;

}
