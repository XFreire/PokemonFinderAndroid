package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.UserSuccess;

import android.content.Context;

import com.alexandrefreire.pokegofinder.Models.User;


/**
 * Created by Alexandre on 20/2/16.
 */
public class UserCreatedPresenterImpl implements UserCreatedPresenter{
    private Context mContext;
    private UserCreatedView mView;
    private User mUser;

    public UserCreatedPresenterImpl(Context context, UserCreatedView view){
        mContext = context;
        mView = view;
    }

    @Override
    public void onCreateView() {
        mUser = User.getCurrentUser();
        if (mUser != null){
            mView.setInfo(mUser);
        }
    }

    @Override
    public void onSearchPokemonClicked() {
        mView.navigateToMain();
    }
}
