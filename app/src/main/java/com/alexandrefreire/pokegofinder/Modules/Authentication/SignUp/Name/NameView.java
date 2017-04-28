package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Name;

/**
 * Created by Alexandre on 19/2/16.
 */
public interface NameView {
    void setProgress(int progress);
    void hideKeyboard();
    void showOkButton();
    void hideOkButton();
    void enableOkButton();
    void disableOkButton();
    void showNavigationLayout();
    void hideNavigationLayout();
    void navigateToNext(String name);
    void navigateToPrevious();
}
