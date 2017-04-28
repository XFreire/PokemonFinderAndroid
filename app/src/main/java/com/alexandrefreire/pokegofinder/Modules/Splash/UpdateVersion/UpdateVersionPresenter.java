package com.alexandrefreire.pokegofinder.Modules.Splash.UpdateVersion;

/**
 * Created by Alexandre on 27/1/16.
 */
public interface UpdateVersionPresenter {
    void onCreateView();
    void checkCurrentUser();
    void onSkipClicked();
    void onUpdateClicked();
}
