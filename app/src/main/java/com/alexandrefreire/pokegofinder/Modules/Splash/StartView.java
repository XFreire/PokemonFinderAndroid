package com.alexandrefreire.pokegofinder.Modules.Splash;

/**
 * Created by Alexandre on 21/7/16.
 */
public interface StartView {
    public void navigateToSignUp();
    public void navigateToSignIn();
    public void navigateToMain();
    public void navigateToUpdateVersion(boolean forceUpdateEnabled, int userAppVersion, int playStoreAppVersion);
}
