package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Name;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Email.EmailActivity;
import com.alexandrefreire.pokegofinder.R;
import com.alexandrefreire.pokegofinder.Utils.TextWatcher.MyTextWatcher;
import com.alexandrefreire.pokegofinder.Utils.TextWatcher.MyTextWatcherImpl;


public class NameFragment extends Fragment implements NameView, MyTextWatcher, View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener {
    public static final String NAME_EXTRA = "NameFragment.NAME_EXTRA";
    private Context mContext;
    private NamePresenter mPresenter;

    //Views
    private View mRoot;
    private EditText mNameEditText;
    private Button mOkButton;
    private ImageView mKeyboardImage;
    private ProgressBar mProgress;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private ViewGroup mNavigationLayout;
    private ViewGroup mContentLayout;
    private RelativeLayout.LayoutParams initParams;

    public NameFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_name, container, false);
        mContext = getActivity();
        mPresenter = new NamePresenterImpl(mContext, this);
        mNameEditText = (EditText) mRoot.findViewById(R.id.name_edidtext);
        mOkButton = (Button) mRoot.findViewById(R.id.ok_btn);
        mKeyboardImage = (ImageView) mRoot.findViewById(R.id.keyboard_img);
        mProgress = (ProgressBar) mRoot.findViewById(R.id.progressBar);
        mNextButton = (ImageButton) mRoot.findViewById(R.id.next_btn);
        mPreviousButton = (ImageButton) mRoot.findViewById(R.id.previous_btn);
        mNavigationLayout = (ViewGroup) mRoot.findViewById(R.id.navigation_layout);
        mContentLayout = (ViewGroup) mRoot.findViewById(R.id.content_layout);
        MyTextWatcherImpl nameWatcher = new MyTextWatcherImpl(mNameEditText, this);
        mNameEditText.addTextChangedListener(nameWatcher);
        mNameEditText.setOnClickListener(this);
        mPreviousButton.setEnabled(false);
        mOkButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mRoot.getViewTreeObserver().addOnGlobalLayoutListener(this);

        mPresenter.onCreateView();
        return mRoot;
    }

    @Override
    public void setProgress(int progress) {
        mProgress.setProgress(progress);
    }

    @Override
    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showOkButton() {
        mNameEditText.setCursorVisible(true);
        mOkButton.setVisibility(View.VISIBLE);
        mKeyboardImage.setVisibility(View.GONE);
    }

    @Override
    public void hideOkButton() {
        mNameEditText.setCursorVisible(false);
        mOkButton.setVisibility(View.GONE);
        mKeyboardImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void enableOkButton() {
        mOkButton.setEnabled(true);
        mNextButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_white_24dp));
        mNextButton.setEnabled(true);
    }

    @Override
    public void disableOkButton() {
        mOkButton.setEnabled(false);
        mNextButton.setEnabled(false);
        mNextButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_blue_24dp));
    }

    @Override
    public void showNavigationLayout() {
        if (mNavigationLayout.getVisibility() == View.GONE){
            mNavigationLayout.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mContentLayout.getLayoutParams();
            params.setMargins(0, -250, 0, 0); //left, top, right, bottom
            mContentLayout.setLayoutParams(params);
        }
    }

    @Override
    public void hideNavigationLayout() {
        if (mNavigationLayout.getVisibility() == View.VISIBLE){
            mNavigationLayout.setVisibility(View.GONE);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mContentLayout.getLayoutParams();
            params.setMargins(0, 0, 0, 0); //left, top, right, bottom
            mContentLayout.setLayoutParams(params);
        }
    }

    @Override
    public void navigateToNext(String name) {
        Intent intent = new Intent(getActivity(), EmailActivity.class);
        intent.putExtra(NAME_EXTRA, name);
        startActivity(intent);
    }

    @Override
    public void navigateToPrevious() {
        //nothing
    }

    @Override
    public void onTextChanged(View view, CharSequence s, int start, int before, int count) {
        mPresenter.onNameChanged(s.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_btn:
            case R.id.next_btn:{
                mPresenter.onNextClicked();
                break;
            }
            case R.id.name_edidtext:{
                showOkButton();
                break;
            }

        }
    }

    @Override
    public void onGlobalLayout() {
        int heightDiff = mRoot.getRootView().getHeight() - mRoot.getHeight();
        if (heightDiff > 600) { // if more than 100 pixels, its probably a keyboard...
            mPresenter.keyboardVisible(true);
        }
        else{
            mPresenter.keyboardVisible(false);
        }
    }
}
