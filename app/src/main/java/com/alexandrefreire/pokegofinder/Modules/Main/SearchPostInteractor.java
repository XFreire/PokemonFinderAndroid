package com.alexandrefreire.pokegofinder.Modules.Main;

import com.alexandrefreire.pokegofinder.Models.Pokemon;

/**
 * Created by Alexandre on 21/7/16.
 */
public interface SearchPostInteractor {
    void searchPokemon(double latitude, double longitude, OnPostSearchFinishedListener listener);
    void searchPokemon(Pokemon pokemon,double latitude, double longitude, OnPostSearchFinishedListener listener);



}
