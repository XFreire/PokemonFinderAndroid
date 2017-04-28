package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Email;


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
import android.widget.TextView;

import com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Password.PasswordActivity;
import com.alexandrefreire.pokegofinder.R;
import com.alexandrefreire.pokegofinder.Utils.TextWatcher.MyTextWatcher;
import com.alexandrefreire.pokegofinder.Utils.TextWatcher.MyTextWatcherImpl;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment implements EmailView, MyTextWatcher, View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener {
    public static final String NAME_EXTRA = "EmailFragment.NAME_EXTRA";
    public static final String EMAIL_EXTRA = "EmailFragment.EMAIL_EXTRA";
    private Context mContext;
    private EmailPresenter mPresenter;

    //Views
    private View mRoot;
    private TextView mEmailTitle;
    private TextView mProgressTitle;
    private EditText mEmailEditText;
    private Button mOkButton;
    private ImageView mKeyboardImage;
    private ProgressBar mProgress;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private ViewGroup mNavigationLayout;
    private ViewGroup mContentLayout;

    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_email, container, false);
        mContext = getActivity();
        mPresenter = new EmailPresenterImpl(mContext, this);
        mEmailEditText = (EditText) mRoot.findViewById(R.id.email_edidtext);
        mEmailTitle = (TextView) mRoot.findViewById(R.id.email_title_textview);
        mProgressTitle = (TextView) mRoot.findViewById(R.id.progress_title);
        mKeyboardImage = (ImageView) mRoot.findViewById(R.id.keyboard_img);
        mOkButton = (Button) mRoot.findViewById(R.id.ok_btn);
        mProgress = (ProgressBar) mRoot.findViewById(R.id.progressBar);
        mNextButton = (ImageButton) mRoot.findViewById(R.id.next_btn);
        mPreviousButton = (ImageButton) mRoot.findViewById(R.id.previous_btn);
        mNavigationLayout = (ViewGroup) mRoot.findViewById(R.id.navigation_layout);
        mContentLayout = (ViewGroup) mRoot.findViewById(R.id.content_layout);
        MyTextWatcherImpl nameWatcher = new MyTextWatcherImpl(mEmailEditText, this);
        mEmailEditText.addTextChangedListener(nameWatcher);
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
    public void setProgressTitle(String title) {
        mProgressTitle.setText(title);
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
        mEmailEditText.setCursorVisible(true);
        mOkButton.setVisibility(View.VISIBLE);
        mKeyboardImage.setVisibility(View.GONE);
    }

    @Override
    public void hideOkButton() {
        mEmailEditText.setCursorVisible(false);
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
        mNavigationLayout.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mContentLayout.getLayoutParams();
        params.setMargins(0, -250, 0, 0); //left, top, right, bottom
        mContentLayout.setLayoutParams(params);
    }

    @Override
    public void hideNavigationLayout() {
        mNavigationLayout.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mContentLayout.getLayoutParams();
        params.setMargins(0, 0, 0, 0); //left, top, right, bottom
        mContentLayout.setLayoutParams(params);
    }

    @Override
    public void setInfo(String name) {
        if (name != null){
            mEmailTitle.setText(getString(R.string.whats_your_email_with_name, name));
        }
        else{
            mEmailTitle.setText(getString(R.string.whats_your_email));
        }
    }

    @Override
    public void navigateToNext(String name, String email) {
        Intent intent = new Intent(getActivity(), PasswordActivity.class);
        intent.putExtra(NAME_EXTRA, name);
        intent.putExtra(EMAIL_EXTRA, email);
        startActivity(intent);
    }

    @Override
    public void navigateToPrevious() {
        getActivity().finish();
    }

    @Override
    public void onTextChanged(View view, CharSequence s, int start, int before, int count) {
        mPresenter.onEmailChanged(s.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_btn:
            case R.id.next_btn:{
                mPresenter.onNextClicked();
                break;
            }
            case R.id.previous_btn:{
                mPresenter.onPreviousClicked();
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
