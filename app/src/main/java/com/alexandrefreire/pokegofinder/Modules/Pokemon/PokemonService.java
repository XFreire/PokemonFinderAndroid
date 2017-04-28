package com.alexandrefreire.pokegofinder.Modules.Pokemon;

import com.alexandrefreire.pokegofinder.Models.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Alexandre on 23/7/16.
 */
public interface PokemonService {
    @GET("pokemons/index")
    Call<List<Pokemon>> downloadPokemons();
}
