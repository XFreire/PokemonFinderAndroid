package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Password;

/**
 * Created by Alexandre on 20/2/16.
 */
public interface PasswordPresenter {
    void onCreateView();
    void keyboardVisible(boolean isKeyboardVisible);
    void onPasswordChanged(String s);
    void onNextClicked();
    void onPreviousClicked();
}
