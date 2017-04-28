package com.alexandrefreire.pokegofinder.Modules.Main;

import com.alexandrefreire.pokegofinder.Models.Post;
import com.google.android.gms.maps.CameraUpdate;

import java.util.List;

/**
 * Created by Alexandre on 21/7/16.
 */
public interface MainView {
    void showProgress(String title, String msg);
    void hideProgress();
    void clearMap();
    public void centerMap(CameraUpdate camUpdate);
    public void addPokemonMarkers(List<Post> pokemonList);
    public void navigateToAddPokemonPost(double lat, double lng);
    public void navigateToSearchPokemon();
}
