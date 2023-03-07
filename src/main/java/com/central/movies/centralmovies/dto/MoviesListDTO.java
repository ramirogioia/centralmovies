package com.central.movies.centralmovies.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class MoviesListDTO {
    
  @JsonProperty("movies")
  @SerializedName("d")
    private List<MovieDTO> movies;

  }
  