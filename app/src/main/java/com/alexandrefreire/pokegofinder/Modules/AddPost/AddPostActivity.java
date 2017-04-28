package com.alexandrefreire.pokegofinder.Modules.AddPost;

import android.support.v4.app.Fragment;

import com.alexandrefreire.pokegofinder.Activities.FragmentContainerActivity;

public class AddPostActivity extends FragmentContainerActivity {

    @Override
    public Fragment createFragment() {
        return new AddPostFragment();
    }
}
