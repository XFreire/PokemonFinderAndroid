package com.alexandrefreire.pokegofinder.Modules.SearchPokemon;

import android.content.Context;

import com.alexandrefreire.pokegofinder.Models.Pokemon;

import java.util.List;

/**
 * Created by Alexandre on 24/7/16.
 */
public class SearchPokemonPresenterImpl implements SearchPokemonPresenter {
    private Context mContext;
    private SearchPokemonView mView;
    private List<Pokemon> mPokemonList;


    public SearchPokemonPresenterImpl(Context context, SearchPokemonView view){
        mContext = context;
        mView = view;
    }

    @Override
    public void onCreate() {
        mPokemonList = Pokemon.listAll(Pokemon.class);
        mView.setPokemonListInfo(mPokemonList);
    }

    @Override
    public void onPokemonNameTextChanged(String pokemonName) {

    }

    @Override
    public void onPokemonSelected(Pokemon pokemon) {
        mView.navigateToMain(pokemon);
    }

    @Override
    public void onBackButtonClicked() {
        mView.navigateToMain(null);
    }
}
