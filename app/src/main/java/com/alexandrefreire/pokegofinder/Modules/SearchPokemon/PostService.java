package com.alexandrefreire.pokegofinder.Modules.SearchPokemon;

import com.alexandrefreire.pokegofinder.Models.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alexandre on 24/7/16.
 */
public interface PostService {
    @GET("posts/index")
    Call<List<Pokemon>> downloadPosts(@Query("post[pokemon_id]") int pokemonId);
}
