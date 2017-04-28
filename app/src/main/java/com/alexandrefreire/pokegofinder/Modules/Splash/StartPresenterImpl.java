package com.alexandrefreire.pokegofinder.Modules.Splash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.alexandrefreire.pokegofinder.Models.User;

/**
 * Created by Alexandre on 21/7/16.
 */
public class StartPresenterImpl implements StartPresenter, OnVersionKillerFinishedListener {
    private StartView mView;
    private Context mContext;
    private VersionKillerInteractor mInteractor;
    private int mVersionCode;

    public StartPresenterImpl(Context context, StartView view){
        mContext = context;
        mView = view;
        mInteractor = new VersionKillerInteractorImpl(mContext);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void checkVersion() {
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            mVersionCode = pInfo.versionCode;
            Log.v("VersionCode:", pInfo.versionCode+"");
            mInteractor.getCurrentVersion(this);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkCurrentUser() {
        User user = User.getCurrentUser();
        if (user != null){
            mView.navigateToMain();
        }
        else{
            mView.navigateToSignUp();
        }
    }

    @Override
    public void onVersionKillerSuccess(int playStoreVersion, boolean forceUpdate) {
        if (playStoreVersion > mVersionCode){
            mView.navigateToUpdateVersion(forceUpdate, mVersionCode, playStoreVersion);
        }
        else{
            checkCurrentUser();
        }
    }

    @Override
    public void onVersionKillerError(String error) {

    }
}
