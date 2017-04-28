package com.alexandrefreire.pokegofinder.Modules.Main;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alexandrefreire.pokegofinder.Activities.MainActivity;
import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.Models.Post;
import com.alexandrefreire.pokegofinder.Modules.AddPost.AddPostActivity;
import com.alexandrefreire.pokegofinder.Modules.SearchPokemon.SearchPokemonActivity;
import com.alexandrefreire.pokegofinder.Modules.SearchPokemon.SearchPokemonFragment;
import com.alexandrefreire.pokegofinder.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MainFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, MainView, GoogleMap.OnCameraChangeListener {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int SEARCH_POKEMON_CODE_RESULT = 1;
    public static final String EXTRA_POKEMON = "MainFragment.EXTRA_POKEMON";
    public static final String LAT_EXTRA =  "MainFragment.EXTRA_LAT";
    public static final String LNG_EXTRA =  "MainFragment.EXTRA_LNG";
    // Vars
    private Context mContext;
    private MainPresenter mPresenter;
    private ProgressDialog mProgress;
    private MapFragment mMapFragment;
    private View mSearchBox;

    //Views
    private GoogleMap mMap;
    private FloatingActionButton mMyLocationButton;
    private ViewGroup mPokemonHereButton;
    private View mMenuButton;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        //My Location Btn
        mMyLocationButton = (FloatingActionButton)root.findViewById(R.id.my_location_btn);
        mMyLocationButton.setOnClickListener(this);
        // Pokemon here btn
        mPokemonHereButton = (ViewGroup) root.findViewById(R.id.pokemon_here_btn);
        mPokemonHereButton.setOnClickListener(this);
        // Search Box
        mSearchBox = root.findViewById(R.id.search_box);
        mSearchBox.findViewById(R.id.search_box_text_view).setOnClickListener(this);
        // Menu Btn
        mMenuButton = (View) root.findViewById(R.id.menu_btn);
        mMenuButton.setOnClickListener(this);

        mContext = getActivity();
        mProgress = new ProgressDialog(mContext, ProgressDialog.STYLE_SPINNER);
        mPresenter = new MainPresenterImpl(mContext, this);
        mMapFragment = (MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.map);

        checkPermissions();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("onActivityResult", "yes");
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case SEARCH_POKEMON_CODE_RESULT:{
                    Pokemon pokemon = data.getParcelableExtra(EXTRA_POKEMON);
                    if (pokemon != null) {
                        mPresenter.doSearchPokemon(pokemon);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraChangeListener(this);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        mPresenter.onCameraPositionChanged(cameraPosition);
    }

    @Override
    public void showProgress(String title, String msg) {
        mProgress.setMessage(msg);
        mProgress.show();
    }

    @Override
    public void hideProgress() {
        mProgress.hide();
    }

    @Override
    public void clearMap() {
        mMap.clear();
    }

    @Override
    public void centerMap(CameraUpdate camUpdate) {
        if (mMap != null){
            mMap.animateCamera(camUpdate);
        }
    }

    @Override
    public void addPokemonMarkers(List<Post> pokemonList) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Post post:pokemonList) {
            LatLng latLng = new LatLng(post.getLatitude(), post.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(post.getPokemon().getName())
                    .icon(BitmapDescriptorFactory.fromResource(post.getPokemon().getPokemonIconId(mContext ))));

            builder.include(latLng);
        }
        if (pokemonList.size() > 0){
            LatLngBounds bounds = builder.build();
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 300);
            mMap.animateCamera(cu);
        }
        else{
            Toast.makeText(mContext, getString(R.id.no_pokemon), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void navigateToAddPokemonPost(double lat, double lng) {
        Intent intent = new Intent(mContext, AddPostActivity.class);
        intent.putExtra(LAT_EXTRA, lat);
        intent.putExtra(LNG_EXTRA, lng);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(getActivity(), mSearchBox,
                            mContext.getString(R.string.transition_search_box));
            
            ActivityCompat.startActivityForResult(getActivity(), intent,
                    SEARCH_POKEMON_CODE_RESULT, options.toBundle());
        }
        else{
            ActivityCompat.startActivityForResult(getActivity(), intent,
                    SEARCH_POKEMON_CODE_RESULT, null);
        }
    }

    @Override
    public void navigateToSearchPokemon() {
        Intent intent = new Intent(mContext, SearchPokemonActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeSceneTransitionAnimation(getActivity(), mSearchBox,
                            mContext.getString(R.string.transition_search_box));

            ActivityCompat.startActivityForResult(getActivity(), intent,
                    SEARCH_POKEMON_CODE_RESULT, options.toBundle());
        }
        else{

            ActivityCompat.startActivityForResult(getActivity(), intent,
                    SEARCH_POKEMON_CODE_RESULT, null);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_btn:{
                ((MainActivity) getActivity()).openDrawer();
                break;
            }
            case R.id.search_box_text_view:{
                mPresenter.onSearchBoxClicked();
                break;
            }
            case R.id.my_location_btn:{
                mPresenter.onMyLocationButtonClicked(mMap.getMyLocation());
                break;
            }
            case R.id.pokemon_here_btn:{
                mPresenter.onPokemonHereClicked();
                break;
            }
        }
    }


    // *** LOCATION PERMISIONS ***
    
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            }
        }
        else{
            mMapFragment.getMapAsync(this);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    mMapFragment.getMapAsync(this);

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}
