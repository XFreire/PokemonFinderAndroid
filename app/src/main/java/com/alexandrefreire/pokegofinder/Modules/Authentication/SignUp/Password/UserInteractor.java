package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Password;

/**
 * Created by Alexandre on 20/2/16.
 */
public interface UserInteractor {
    void createUser(String name, String email, String password, OnUserFinishedListener listener);

    void signIn(String mEmail, String mPassword, OnUserFinishedListener listener);
}
