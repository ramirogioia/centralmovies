package com.central.movies.centralmovies.dto;

import java.lang.reflect.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

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
@Entity(name = "movies")
public class MovieDTO {

  @Id
  @JsonProperty("movie_id")
  @Column(name = "movie_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  Long movieId;

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
