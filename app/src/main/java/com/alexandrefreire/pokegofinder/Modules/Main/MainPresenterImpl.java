package com.alexandrefreire.pokegofinder.Modules.Main;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.Models.Post;
import com.alexandrefreire.pokegofinder.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Alexandre on 21/7/16.
 */
public class MainPresenterImpl implements MainPresenter, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, OnPostSearchFinishedListener {
    private final GoogleApiClient mGoogleApiClient;
    private Context mContext;
    private MainView mView;
    private SearchPostInteractor mInteractor;
    private Location mUserLocation;
    private LocationRequest mLocationRequest;

    public MainPresenterImpl(Context context, MainView view) {
        mContext = context;
        mView = view;
        mInteractor = new SearchPostInteractorImpl(mContext);
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onResume() {
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
        }
    }

    @Override
    public void onPause() {
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        createLocationRequest();
        if (lastLocation != null) {
            mUserLocation = lastLocation;
            doSearchNearby(mUserLocation.getLatitude(), mUserLocation.getLongitude());
            LatLng latLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            if (cameraUpdate != null){
                mView.centerMap(cameraUpdate);
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i("onConnectionSuspended", String.valueOf(i));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("onConnectionFailed", connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        mView.clearMap();
        mUserLocation = location;
        //doSearchNearby(location.getLatitude(), location.getLongitude());
        Log.i("Location", location.getLatitude()+", "+location.getLongitude());
        CameraUpdate camUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15);
        mView.centerMap(camUpdate);
        stopLocationUpdates();
    }

    @Override
    public void doSearchNearby(double latitude, double longitude) {
        mInteractor.searchPokemon(latitude, longitude, this);
    }

    @Override
    public void doSearchPokemon(Pokemon pokemon) {
        mView.showProgress(null, mContext.getString(R.string.searching_pokemon_name, pokemon.getName()));
        mInteractor.searchPokemon(pokemon, mUserLocation.getLatitude(), mUserLocation.getLongitude(), this);
    }

    @Override
    public void onCameraPositionChanged(CameraPosition cameraPosition) {

    }

    @Override
    public void onMyLocationButtonClicked(Location myLocation) {
        if (myLocation != null){
            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            mView.centerMap(cameraUpdate);
        }
    }

    @Override
    public void onPokemonHereClicked() {
        mView.navigateToAddPokemonPost(mUserLocation.getLatitude(), mUserLocation.getLongitude());
    }

    @Override
    public void onSearchBoxClicked() {
        mView.navigateToSearchPokemon();
    }

    private void createLocationRequest() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    @Override
    public void onPokemonSearchSuccess(List<Post> pokemonList) {
        mView.hideProgress();
        mView.clearMap();
        mView.addPokemonMarkers(pokemonList);
    }

    @Override
    public void onPokemonSearchError(String error) {

    }
}
