package com.central.movies.centralmovies.dto;

import java.lang.reflect.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Movies")
public class MovieDTO {

  @Id
  @JsonProperty("movie_id")
  @Column(name = "movie_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long movieId;

  @JsonProperty("imdb_id")
  @Column(name = "imdb_id")
  @SerializedName("id")
  private String imdbId;

  @JsonProperty("name")
  @SerializedName("l")
  @Column
  private String name;

  @JsonProperty("actors")
  @SerializedName("s")
  @Column
  private String actors;

  @JsonProperty("year")
  @SerializedName("y")
  @Column
  private int year;

  @JsonProperty("ranking")
  @SerializedName("rank")
  @Column
  private int ranking;

  @JsonProperty("image")
  @SerializedName("i")
  @JsonAdapter(UsernameDeserializer.class)
  @Column
  private String image;

  public static class UsernameDeserializer implements JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
      return json.getAsJsonObject().get("imageUrl").getAsString();
    }
  }

  public void setId(String imdbId) {
    this.imdbId = imdbId;
  }

}
