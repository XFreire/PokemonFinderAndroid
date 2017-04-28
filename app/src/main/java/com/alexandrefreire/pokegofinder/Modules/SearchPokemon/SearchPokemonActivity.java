package com.alexandrefreire.pokegofinder.Modules.SearchPokemon;

import android.support.v4.app.Fragment;

import com.alexandrefreire.pokegofinder.Activities.FragmentContainerActivity;

public class SearchPokemonActivity extends FragmentContainerActivity {

    @Override
    public Fragment createFragment() {
        return new SearchPokemonFragment();
    }
}
