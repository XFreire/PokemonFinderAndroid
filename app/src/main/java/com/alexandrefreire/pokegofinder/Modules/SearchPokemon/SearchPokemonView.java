package com.alexandrefreire.pokegofinder.Modules.SearchPokemon;

import com.alexandrefreire.pokegofinder.Models.Pokemon;

import java.util.List;

/**
 * Created by Alexandre on 24/7/16.
 */
public interface SearchPokemonView {
    /*void showProgress(String title, String msg);
    void hideProgress();*/
    void setPokemonListInfo(List<Pokemon> list);
    void navigateToMain(Pokemon pokemon);
}
