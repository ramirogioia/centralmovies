package com.central.movies.centralmovies.utils;

import java.lang.reflect.Type;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonHelper {

    private GsonHelper() {
    }

    public static final Gson customGson = new GsonBuilder().setPrettyPrinting()
            // .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter()).create();

    private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {

        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            return Base64.decodeBase64(json.getAsString());
        }

        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.encodeBase64String(src));
        }
    }
}