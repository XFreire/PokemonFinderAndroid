package com.alexandrefreire.pokegofinder.Modules.Main;

import com.alexandrefreire.pokegofinder.Models.Post;

import java.util.List;

/**
 * Created by Alexandre on 21/7/16.
 */
public interface OnPostSearchFinishedListener {
    void onPokemonSearchSuccess(List<Post> pokemonList);
    void onPokemonSearchError(String error);
}
