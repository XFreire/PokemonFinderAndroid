package com.alexandrefreire.pokegofinder.Modules.Splash.UpdateVersion;

import android.support.v4.app.Fragment;

import com.alexandrefreire.pokegofinder.Activities.FragmentContainerActivity;


public class UpdateVersionActivity extends FragmentContainerActivity {

    @Override
    public Fragment createFragment() {
        return new UpdateVersionFragment();
    }
}
