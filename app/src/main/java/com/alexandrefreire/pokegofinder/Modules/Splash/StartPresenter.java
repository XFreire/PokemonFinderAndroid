package com.alexandrefreire.pokegofinder.Modules.Splash;

/**
 * Created by Alexandre on 21/7/16.
 */
public interface StartPresenter {
    void onPause();
    void onResume();
    void checkVersion();
    public void checkCurrentUser();
}
