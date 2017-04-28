package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Name;

import android.content.Context;

/**
 * Created by Alexandre on 19/2/16.
 */
public class NamePresenterImpl implements NamePresenter{
    private Context mContext;
    private NameView mView;
    private String mName = "";

    public NamePresenterImpl(Context context, NameView view){
        mContext = context;
        mView = view;
    }

    @Override
    public void onCreateView() {
        mView.setProgress(0);
    }

    @Override
    public void onNextClicked() {
        mView.hideKeyboard();
        mView.navigateToNext(mName);
    }

    @Override
    public void onPreviousClicked() {
        //nothing
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
    public void onNameChanged(String s) {
        mName = s;
        if (!s.equals("")){
            mView.enableOkButton();
        }
        else{
            mView.disableOkButton();
        }
    }
}
