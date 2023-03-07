package com.central.movies.centralmovies.dto;

import java.lang.reflect.Type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

  @JsonProperty("id")
  @SerializedName("id")
  private String id;

  @JsonProperty("name")
  @SerializedName("l")
  private String name;

  @JsonProperty("actors")
  @SerializedName("s")
  private String actors;

  @JsonProperty("year")
  @SerializedName("y")
  private int year;

  @JsonProperty("ranking")
  @SerializedName("rank")
  private int ranking;

  @JsonProperty("image")
  @SerializedName("i")
  @JsonAdapter(UsernameDeserializer.class)
  private String image;


  public static class UsernameDeserializer implements JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return json.getAsJsonObject().get("imageUrl").getAsString();
    }
}

}
