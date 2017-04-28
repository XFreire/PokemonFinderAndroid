package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Email;

/**
 * Created by Alexandre on 20/2/16.
 */
public interface EmailPresenter {
    void onCreateView();
    void keyboardVisible(boolean isKeyboardVisible);
    void onEmailChanged(String s);
    void onNextClicked();
    void onPreviousClicked();
}
