package com.alexandrefreire.pokegofinder.Modules.Splash.UpdateVersion;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.alexandrefreire.pokegofinder.Models.User;
import com.alexandrefreire.pokegofinder.Modules.Splash.StartActivity;


/**
 * Created by Alexandre on 27/1/16.
 */
public class UpdateVersionPresenterImpl  implements UpdateVersionPresenter{
    private Context mContext;
    private UpdateVersionView mView;
    private int mVersionCode;
    private int mPlayStoreVersionCode;
    private boolean mForceUpdateEnabled;
    //private Tracker mTracker;

    public UpdateVersionPresenterImpl(Context context, UpdateVersionView view){
        mContext = context;
        mView = view;
        //mTracker =  new Tracker(mContext);
    }
    @Override
    public void onCreateView() {
        Bundle bundle = ((Activity) mContext).getIntent().getExtras();
        if (bundle != null){
            mForceUpdateEnabled = bundle.getBoolean(StartActivity.EXTRA_FORCE_UPDATED_ENABLED);
            mVersionCode = bundle.getInt(StartActivity.EXTRA_USER_VERSION);
            mPlayStoreVersionCode = bundle.getInt(StartActivity.EXTRA_PLAY_STORE_VERSION);
            if (mForceUpdateEnabled){
                mView.enableForceUpdateMode();
            }
            else{
                mView.disableForceUpdateMode();
            }
        }
        //mTracker.trackUpdateAppView(mVersionCode, mPlayStoreVersionCode);
    }

    @Override
    public void checkCurrentUser() {
        User user = User.getCurrentUser();
        if (user != null){
            mView.navigateToMain();
        }
        else{
            mView.navigateToSignIn();
        }
    }

    @Override
    public void onSkipClicked() {
        //mTracker.trackSkipUpdateApp(mVersionCode, mPlayStoreVersionCode);
        checkCurrentUser();
    }

    @Override
    public void onUpdateClicked() {
        //mTracker.trackUpdateAppButton(mVersionCode, mPlayStoreVersionCode);
        mView.navigateToPlayStore();
    }
}
