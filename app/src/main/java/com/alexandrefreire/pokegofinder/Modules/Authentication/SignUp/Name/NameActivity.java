package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Name;

import android.support.v4.app.Fragment;

import com.alexandrefreire.pokegofinder.Activities.FragmentContainerActivity;


public class NameActivity extends FragmentContainerActivity {

    @Override
    public Fragment createFragment() {
        return new NameFragment();
    }
}
