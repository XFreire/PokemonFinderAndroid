package com.alexandrefreire.pokegofinder.Modules.Splash;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Alexandre on 21/7/16.
 */
public interface VersionKillerService {
    @GET("version_killer")
    Call<JsonObject> version();
}
