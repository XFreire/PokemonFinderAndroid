package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.UserSuccess;

import android.support.v4.app.Fragment;

import com.alexandrefreire.pokegofinder.Activities.FragmentContainerActivity;


public class UserCreatedActivity extends FragmentContainerActivity {

    @Override
    public Fragment createFragment() {
        return new UserCreatedFragment();
    }
}
