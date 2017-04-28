package com.alexandrefreire.pokegofinder.Modules.SearchPokemon;

import com.alexandrefreire.pokegofinder.Models.Post;

import java.util.List;

/**
 * Created by Alexandre on 24/7/16.
 */
public interface OnSearchPokemonLFinishedListener {
    void onSearchPokemonSuccess(List<Post> postList);
    void onSearchPokemonError(String error);

}
