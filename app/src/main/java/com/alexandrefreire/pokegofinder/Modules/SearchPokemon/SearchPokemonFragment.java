package com.alexandrefreire.pokegofinder.Modules.SearchPokemon;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.Modules.Main.MainFragment;
import com.alexandrefreire.pokegofinder.R;

import java.util.List;

public class SearchPokemonFragment extends Fragment implements SearchPokemonView, View.OnClickListener, AdapterView.OnItemClickListener {
    private Context mContext;
    private SearchPokemonPresenter mPresenter;
    private ViewGroup mSearchBox;
    private AutoCompleteTextView mAutocompleteTextBox;
    private ImageButton mBackButton;
    private ArrayAdapter mPokemonAdapter;

    public SearchPokemonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_search_pokemon, container, false);
        mContext = getActivity();
        mPresenter = new SearchPokemonPresenterImpl(mContext, this);
        mSearchBox = (ViewGroup) root.findViewById(R.id.search_box);
        mBackButton = (ImageButton) mSearchBox.findViewById(R.id.menu_btn);
        mBackButton.setImageResource(R.drawable.ic_keyboard_backspace_gray_24dp);
        mBackButton.setOnClickListener(this);
        mAutocompleteTextBox = (AutoCompleteTextView) mSearchBox.findViewById(R.id.search_box_text_view);
        mAutocompleteTextBox.setHint(R.string.type_pokemon_name);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mPresenter.onCreate();
        return root;
    }

    @Override
    public void setPokemonListInfo(List<Pokemon> list) {
        mPokemonAdapter = new PokemonArrayAdapter(mContext, R.layout.pokemon_listitem, list);
        mAutocompleteTextBox.setAdapter(mPokemonAdapter);
        mAutocompleteTextBox.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Pokemon pokemon = (Pokemon) mPokemonAdapter.getItem(i);
        Log.i("pokemon selected", pokemon.toString());
        mPresenter.onPokemonSelected(pokemon);
    }


    @Override
    public void navigateToMain(Pokemon pokemon) {
        Intent resultIntent = getActivity().getIntent();
        if (resultIntent != null){
            resultIntent.putExtra(MainFragment.EXTRA_POKEMON, pokemon);
            getActivity().setResult(Activity.RESULT_OK, resultIntent);
            getActivity().finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_btn:{
                getActivity().finish();
                break;
            }
        }
    }
}
