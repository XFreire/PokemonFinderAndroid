package com.alexandrefreire.pokegofinder.Modules.Splash;

/**
 * Created by Alexandre on 21/7/16.
 */
public interface OnVersionKillerFinishedListener {
    void onVersionKillerSuccess(int version, boolean forceUpdate);
    void onVersionKillerError(String error);

}
