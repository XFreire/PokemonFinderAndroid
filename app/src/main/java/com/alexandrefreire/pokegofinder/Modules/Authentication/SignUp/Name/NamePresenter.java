package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Name;

/**
 * Created by Alexandre on 19/2/16.
 */
public interface NamePresenter {
    void onCreateView();
    void keyboardVisible(boolean isKeyboardVisible);
    void onNameChanged(String s);
    void onNextClicked();
    void onPreviousClicked();


}
