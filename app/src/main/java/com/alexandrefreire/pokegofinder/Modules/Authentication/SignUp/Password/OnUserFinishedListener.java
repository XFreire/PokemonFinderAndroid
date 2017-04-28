package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Password;


import com.alexandrefreire.pokegofinder.Models.User;

/**
 * Created by Alexandre on 20/2/16.
 */
public interface OnUserFinishedListener {
    void onUsernameError();

    void onPasswordError();

    void onSuccess(User user);
}
