package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Password;


import android.app.ProgressDialog;
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

import com.alexandrefreire.pokegofinder.Activities.MainActivity;
import com.alexandrefreire.pokegofinder.Models.User;
import com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.UserSuccess.UserCreatedActivity;
import com.alexandrefreire.pokegofinder.R;
import com.alexandrefreire.pokegofinder.Utils.TextWatcher.MyTextWatcher;
import com.alexandrefreire.pokegofinder.Utils.TextWatcher.MyTextWatcherImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFragment extends Fragment implements PasswordView, MyTextWatcher, View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener {
    public static final String USER_EXTRA = "USER_EXTRA";
    private Context mContext;
    private PasswordPresenter mPresenter;

    //Views
    private View mRoot;
    private EditText mPasswordEditText;
    private TextView mTitle;
    private TextView mProgressTitle;
    private Button mOkButton;
    private ProgressBar mProgressBar;
    private ImageView mKeyboardImage;
    private ProgressDialog mProgressDialog;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private ViewGroup mNavigationLayout;
    private ViewGroup mContentLayout;

    public PasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_password, container, false);
        mContext = getActivity();
        mPresenter = new PasswordPresenterImpl(mContext, this);
        mProgressDialog = new ProgressDialog(getActivity(), ProgressDialog.STYLE_SPINNER);
        mPasswordEditText = (EditText) mRoot.findViewById(R.id.password_edidtext);
        mTitle = (TextView) mRoot.findViewById(R.id.title);
        mProgressTitle = (TextView) mRoot.findViewById(R.id.progress_title);
        mOkButton = (Button) mRoot.findViewById(R.id.ok_btn);
        mKeyboardImage = (ImageView) mRoot.findViewById(R.id.keyboard_img);
        mProgressBar = (ProgressBar) mRoot.findViewById(R.id.progressBar);
        mNextButton = (ImageButton) mRoot.findViewById(R.id.next_btn);
        mPreviousButton = (ImageButton) mRoot.findViewById(R.id.previous_btn);
        mNavigationLayout = (ViewGroup) mRoot.findViewById(R.id.navigation_layout);
        mContentLayout = (ViewGroup) mRoot.findViewById(R.id.content_layout);
        MyTextWatcherImpl watcher = new MyTextWatcherImpl(mPasswordEditText, this);
        mPasswordEditText.addTextChangedListener(watcher);
        mOkButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mRoot.getViewTreeObserver().addOnGlobalLayoutListener(this);

        mPresenter.onCreateView();
        return mRoot;
    }

    @Override
    public void setProgress(int progress) {
        mProgressBar.setProgress(66);
    }

    @Override
    public void showProgress(String title, String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.hide();
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
        mPasswordEditText.setCursorVisible(true);
        mOkButton.setVisibility(View.VISIBLE);
        mKeyboardImage.setVisibility(View.GONE);
    }

    @Override
    public void hideOkButton() {
        mPasswordEditText.setCursorVisible(false);
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
    public void navigateToUserCreatedSuccessfully(User user) {
        Intent intent = new Intent(getActivity(), UserCreatedActivity.class);
        startActivity(intent);
    }


    @Override
    public void navigateToPrevious() {
        getActivity().finish();
    }

    @Override
    public void navigateToMain(User mUser) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setProgressTitle(String title) {
        mProgressTitle.setText(title);
        mTitle.setText(R.string.enter_your_password);
    }

    @Override
    public void onTextChanged(View view, CharSequence s, int start, int before, int count) {
        mPresenter.onPasswordChanged(s.toString());
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
        if (heightDiff > 600) { // if more than 600 pixels, its probably a keyboard...
            mPresenter.keyboardVisible(true);
        }
        else{
            mPresenter.keyboardVisible(false);
        }
    }
}
