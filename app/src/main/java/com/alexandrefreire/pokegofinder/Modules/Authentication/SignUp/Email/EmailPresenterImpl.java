package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Email;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Name.NameFragment;
import com.alexandrefreire.pokegofinder.R;


/**
 * Created by Alexandre on 20/2/16.
 */
public class EmailPresenterImpl implements EmailPresenter {
    private Context mContext;
    private EmailView mView;
    private String mName = null;
    private String mEmail = null;

    public EmailPresenterImpl(Context context, EmailView view){
        mContext = context;
        mView = view;
    }


    @Override
    public void onCreateView() {
        mView.setProgress(33);
        Bundle extras = ((Activity) mContext).getIntent().getExtras();
        if (extras != null){
            mName = extras.getString(NameFragment.NAME_EXTRA);
            mView.setInfo(mName);
        }
        if (mName == null){
            mView.setProgress(0);
            mView.setProgressTitle(mContext.getString(R.string.sign_in));
        }
    }

    @Override
    public void keyboardVisible(boolean isKeyboardVisible) {
        if (isKeyboardVisible){
            mView.hideNavigationLayout();
            mView.showOkButton();
        }
        else{
            mView.showNavigationLayout();
            mView.hideOkButton();
        }
    }

    @Override
    public void onEmailChanged(String s) {
        mEmail = s;
        //TODO email validation
        if (!s.equals("")){
            mView.enableOkButton();
        }
        else{
            mView.disableOkButton();
        }
    }

    @Override
    public void onNextClicked() {
        mView.hideKeyboard();
        mView.navigateToNext(mName, mEmail);
    }

    @Override
    public void onPreviousClicked() {
        mView.navigateToPrevious();
    }
}
