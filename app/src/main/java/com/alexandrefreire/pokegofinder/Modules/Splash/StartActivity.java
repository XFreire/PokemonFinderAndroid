package com.alexandrefreire.pokegofinder.Modules.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alexandrefreire.pokegofinder.Activities.MainActivity;
import com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.SignUpScreen.SignUpActivity;
import com.alexandrefreire.pokegofinder.R;

public class StartActivity extends AppCompatActivity implements StartView{
    public static final String EXTRA_FORCE_UPDATED_ENABLED = "StartActivity.EXTRA_FORCE_UPDATED_ENABLED";
    public static final String EXTRA_USER_VERSION = "StartActivity.EXTRA_USER_VERSION";
    public static final String EXTRA_PLAY_STORE_VERSION = "StartActivity.EXTRA_PLAY_STORE_VERSION";

    private StartPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mPresenter = new StartPresenterImpl(this, this);
        mPresenter.checkVersion();
    }

    @Override
    public void navigateToSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToSignIn() {

    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToUpdateVersion(boolean forceUpdateEnabled, int userAppVersion, int playStoreAppVersion) {

    }
}
