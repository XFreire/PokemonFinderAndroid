package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Password;


import com.alexandrefreire.pokegofinder.Models.User;

/**
 * Created by Alexandre on 20/2/16.
 */
public interface PasswordView {
    void setProgress(int progress);
    void showProgress(String title, String msg);
    void hideProgress();
    void hideKeyboard();
    void showOkButton();
    void hideOkButton();
    void enableOkButton();
    void disableOkButton();
    void showNavigationLayout();
    void hideNavigationLayout();
    void navigateToUserCreatedSuccessfully(User user);
    void navigateToPrevious();
    void navigateToMain(User mUser);
    void setProgressTitle(String title);
}
