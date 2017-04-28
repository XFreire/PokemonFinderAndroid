package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Email;

/**
 * Created by Alexandre on 20/2/16.
 */
public interface EmailView {
    void setProgress(int progress);
    void setProgressTitle(String title);
    void hideKeyboard();
    void showOkButton();
    void hideOkButton();
    void enableOkButton();
    void disableOkButton();
    void showNavigationLayout();
    void hideNavigationLayout();
    void setInfo(String mName);
    void navigateToNext(String name, String email);
    void navigateToPrevious();


}
