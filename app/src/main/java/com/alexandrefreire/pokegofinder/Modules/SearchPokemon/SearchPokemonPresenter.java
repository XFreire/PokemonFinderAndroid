package com.alexandrefreire.pokegofinder.Modules.SearchPokemon;

import com.alexandrefreire.pokegofinder.Models.Pokemon;

/**
 * Created by Alexandre on 24/7/16.
 */
public interface SearchPokemonPresenter {
    void onCreate();
    void onPokemonNameTextChanged(String pokemonName);
    void onPokemonSelected(Pokemon pokemon);
    void onBackButtonClicked();

}
