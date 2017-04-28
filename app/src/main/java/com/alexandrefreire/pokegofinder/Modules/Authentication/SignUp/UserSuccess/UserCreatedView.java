package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.UserSuccess;


import com.alexandrefreire.pokegofinder.Models.User;

/**
 * Created by Alexandre on 20/2/16.
 */
public interface UserCreatedView {
    void setInfo(User user);
    void navigateToMain();
}
