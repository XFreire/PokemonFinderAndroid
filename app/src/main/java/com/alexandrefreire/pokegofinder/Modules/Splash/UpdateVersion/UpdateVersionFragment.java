package com.alexandrefreire.pokegofinder.Modules.Splash.UpdateVersion;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alexandrefreire.pokegofinder.Activities.MainActivity;
import com.alexandrefreire.pokegofinder.R;

public class UpdateVersionFragment extends Fragment implements UpdateVersionView, View.OnClickListener {
    private Context mContext;
    private UpdateVersionPresenter mPresenter;
    private TextView mInfoTextView;
    private Button mUpdateButton;
    private Button mSkipButton;

    public UpdateVersionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_update_version, container, false);
        mContext = getActivity();
        mPresenter = new UpdateVersionPresenterImpl(mContext, this);
        mInfoTextView = (TextView) root.findViewById(R.id.info_textview);
        mUpdateButton = (Button) root.findViewById(R.id.update_btn);
        mSkipButton = (Button) root.findViewById(R.id.later_btn);
        mUpdateButton.setOnClickListener(this);
        mSkipButton.setOnClickListener(this);
        mPresenter.onCreateView();
        return root;
    }

    @Override
    public void enableForceUpdateMode() {
        mInfoTextView.setText(getString(R.string.force_update_msg));
        mSkipButton.setVisibility(View.GONE);
    }

    @Override
    public void disableForceUpdateMode() {
        mInfoTextView.setText(getString(R.string.there_is_new_version));
        mSkipButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToSignUp() {
        /*Intent intent = new Intent(getActivity(), SignUpActivity.class);
        startActivity(intent);
        getActivity().finish();*/
    }

    @Override
    public void navigateToPlayStore() {
        final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    @Override
    public void navigateToSignIn() {
        /*Intent intent = new Intent(getActivity(), SignInActivity.class);
        startActivity(intent);
        getActivity().finish();*/
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_btn:{
                mPresenter.onUpdateClicked();
                break;
            }
            case R.id.later_btn:{
                mPresenter.onSkipClicked();
                break;
            }
            default:
                break;
        }
    }
}
