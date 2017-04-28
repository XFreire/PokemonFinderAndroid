package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.SignUpScreen;

import android.support.v4.app.Fragment;

import com.alexandrefreire.pokegofinder.Activities.FragmentContainerActivity;


public class SignUpActivity extends FragmentContainerActivity {

    @Override
    public Fragment createFragment() {
        return new SignUpFragment();
    }
}
