package com.alexandrefreire.pokegofinder.Modules.Main;

import android.location.Location;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.google.android.gms.maps.model.CameraPosition;

/**
 * Created by Alexandre on 21/7/16.
 */
public interface MainPresenter {
    void onResume();
    void onPause();
    void doSearchNearby(double latitude, double longitude);
    void doSearchPokemon(Pokemon pokemon);
    void onCameraPositionChanged(CameraPosition cameraPosition);
    void onMyLocationButtonClicked(Location myLocation);
    void onPokemonHereClicked();
    void onSearchBoxClicked();
}
