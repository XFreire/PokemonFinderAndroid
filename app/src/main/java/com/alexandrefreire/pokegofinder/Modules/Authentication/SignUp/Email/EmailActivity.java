package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Email;

import android.support.v4.app.Fragment;

import com.alexandrefreire.pokegofinder.Activities.FragmentContainerActivity;


public class EmailActivity extends FragmentContainerActivity {
    @Override
    public Fragment createFragment() {
        return new EmailFragment();
    }
}
