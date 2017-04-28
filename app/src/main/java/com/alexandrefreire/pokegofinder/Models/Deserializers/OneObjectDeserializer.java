package com.alexandrefreire.pokegofinder.Models.Deserializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Alexandre on 31/10/15.
 */
public class OneObjectDeserializer<T> implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // Get the "content" element from the parsed JSON
        JsonElement content = json.getAsJsonObject();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return gson.fromJson(content, typeOfT);
    }
}
