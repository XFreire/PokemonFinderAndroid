package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Password;

import android.support.v4.app.Fragment;

import com.alexandrefreire.pokegofinder.Activities.FragmentContainerActivity;


public class PasswordActivity extends FragmentContainerActivity {

    @Override
    public Fragment createFragment() {
        return new PasswordFragment();
    }
}
