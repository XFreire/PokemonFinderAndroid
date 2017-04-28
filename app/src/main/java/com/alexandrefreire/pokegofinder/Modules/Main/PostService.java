package com.alexandrefreire.pokegofinder.Modules.Main;

import com.alexandrefreire.pokegofinder.Models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Alexandre on 21/7/16.
 */
public interface PostService {
    @GET("posts/index")
    Call<List<Post>> searchPokemon(@Query("post[latitude]") double latitude,
                                   @Query("post[longitude]") double longitude);
    @GET("posts/index")
    Call<List<Post>> searchPokemon(@Query("post[pokemon_id]") double pokemonId,
                                   @Query("post[latitude]") double latitude,
                                   @Query("post[longitude]") double longitude);

    @FormUrlEncoded
    @POST("posts/create.json")
    Call<Post> addPost(@Field("post[latitude]") Double  latitude,
                      @Field("post[longitude]") Double  longitude,
                      @Field("post[pokemon_id]") int  pokemonId,
                      @Field("post[user_id]") int  userId);
}
