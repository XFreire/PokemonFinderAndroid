package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Password;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.Models.User;
import com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Email.EmailFragment;
import com.alexandrefreire.pokegofinder.Modules.Pokemon.OnDownloadPokemonFinishedListener;
import com.alexandrefreire.pokegofinder.Modules.Pokemon.Persistence;
import com.alexandrefreire.pokegofinder.Modules.Pokemon.PersistenceImpl;
import com.alexandrefreire.pokegofinder.Modules.Pokemon.PokemonInteractor;
import com.alexandrefreire.pokegofinder.Modules.Pokemon.PokemonInteractorImpl;
import com.alexandrefreire.pokegofinder.R;

import java.util.List;


/**
 * Created by Alexandre on 20/2/16.
 */
public class PasswordPresenterImpl implements PasswordPresenter, OnUserFinishedListener, OnDownloadPokemonFinishedListener {
    private Context mContext;
    private PasswordView mView;
    private UserInteractor mUserInteractor;
    private PokemonInteractor mPokemonInteractor;
    private User mUser;

    private String mName = null;
    private String mEmail = null;
    private String mPassword = null;
    private boolean mSignInMode;

    public PasswordPresenterImpl(Context context, PasswordView view){
        mContext = context;
        mView = view;
        mUserInteractor = new UserInteractorImpl();
        mPokemonInteractor = new PokemonInteractorImpl();
    }

    @Override
    public void onCreateView() {
        mView.setProgress(86);
        Bundle extras = ((Activity) mContext).getIntent().getExtras();
        if (extras != null){
            mName = extras.getString(EmailFragment.NAME_EXTRA);
            mEmail = extras.getString(EmailFragment.EMAIL_EXTRA);
        }
        if (mName == null){
            mSignInMode = true;
            mView.setProgress(60);
            mView.setProgressTitle(mContext.getString(R.string.sign_in));
        }
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
    public void onPasswordChanged(String s) {
        mPassword = s;
        if (s.length() >= 8){
            mView.enableOkButton();
        }
        else{
            mView.disableOkButton();
        }
    }

    @Override
    public void onNextClicked() {
        mView.hideKeyboard();
        if (mSignInMode) {
            mView.showProgress("", mContext.getString(R.string.signing_in));
            mUserInteractor.signIn(mEmail, mPassword, this);
        }
        else {
            mView.showProgress("", mContext.getString(R.string.creating_account));
            mUserInteractor.createUser(mName, mEmail, mPassword, this);
        }
    }

    @Override
    public void onPreviousClicked() {
        mView.navigateToPrevious();
    }

    @Override
    public void onUsernameError() {
        mView.hideProgress();
    }

    @Override
    public void onPasswordError() {
        mView.hideProgress();
    }

    @Override
    public void onSuccess(User user) {
        mUser = user;
        new PersistenceImpl().save(mUser);
        mPokemonInteractor.getPokemons(this);
    }

    @Override
    public void onDownloadPokemonSuccess(List<Pokemon> list) {
        mView.hideProgress();
        Persistence persistence = new PersistenceImpl();
        for(Pokemon pokemon:list){
            persistence.save(pokemon);
        }
        if (mSignInMode){
            mView.navigateToMain(mUser);
        }
        else {
            mView.navigateToUserCreatedSuccessfully(mUser);
        }
    }

    @Override
    public void onDownloadPokemonError(String error) {

    }
}
