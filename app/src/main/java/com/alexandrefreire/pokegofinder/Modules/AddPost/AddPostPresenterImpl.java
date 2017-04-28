package com.alexandrefreire.pokegofinder.Modules.AddPost;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.Models.Post;
import com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Email.EmailFragment;
import com.alexandrefreire.pokegofinder.Modules.Main.MainFragment;

import java.util.List;

/**
 * Created by Alexandre on 29/7/16.
 */
public class AddPostPresenterImpl implements AddPostPresenter, AddPostFinishedListener {
    private Context mContext;
    private AddPostView mView;
    private Pokemon mPokemon;
    private String mPokemonName = "";
    private List<Pokemon> mPokemonList;
    private double mLongitude;
    private double mLatitude;
    private AddPostInteractor mInteractor;

    public AddPostPresenterImpl(Context context, AddPostView view) {
        mContext = context;
        mView = view;
        mInteractor = new AddPostInteractorImpl();
    }

    @Override
    public void onCreateView() {
        mView.setProgress(70);
        mPokemonList = Pokemon.listAll(Pokemon.class);
        mView.setPokemonListInfo(mPokemonList);
        Bundle extras = ((Activity) mContext).getIntent().getExtras();
        if (extras != null){
            mLatitude = extras.getDouble(MainFragment.LAT_EXTRA);
            mLongitude = extras.getDouble(MainFragment.LNG_EXTRA);
        }
    }

    @Override
    public void onNextClicked() {
        mView.hideKeyboard();
        mInteractor.addPost(mLatitude, mLongitude, mPokemon, this);

    }

    @Override
    public void onPreviousClicked() {
        //nothing
    }

    @Override
    public void keyboardVisible(boolean isKeyboardVisible) {
        if (isKeyboardVisible){
            mView.hideNavigationLayout();
            mView.showOkButton();
        }
        else{
            mView.showNavigationLayout();
            mView.hideOkButton();
        }
    }

    @Override
    public void onNameChanged(String s) {
        mPokemonName = s;
        if (!s.equals("")){
            mView.enableOkButton();
        }
        else{
            mView.disableOkButton();
        }
    }

    @Override
    public void onPokemonSelected(Pokemon pokemon) {
        mPokemon = pokemon;
    }

    @Override
    public void onAddPostSuccess(Post post) {
        mView.navigateToMain();
    }

    @Override
    public void onAddPostError() {

    }
}
