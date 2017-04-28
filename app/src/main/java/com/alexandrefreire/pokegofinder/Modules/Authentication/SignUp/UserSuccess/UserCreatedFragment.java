package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.UserSuccess;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alexandrefreire.pokegofinder.Activities.MainActivity;
import com.alexandrefreire.pokegofinder.Models.User;
import com.alexandrefreire.pokegofinder.R;

public class UserCreatedFragment extends Fragment implements UserCreatedView, View.OnClickListener {
    public final static String USER_EXTRA = "USER_EXTRA";
    private Context mContext;
    private UserCreatedPresenter mPresenter;
    //Views
    private TextView mTitleTextView;
    private Button mAddPaymentButton;
    private TextView mTryDemoButton;

    public UserCreatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user_created, container, false);
        mContext = getActivity();
        mPresenter = new UserCreatedPresenterImpl(mContext, this);
        mTitleTextView = (TextView) root.findViewById(R.id.title);
        mAddPaymentButton = (Button) root.findViewById(R.id.add_payment_btn);
        mTryDemoButton = (TextView) root.findViewById(R.id.try_demo_btn);
        mAddPaymentButton.setOnClickListener(this);
        mTryDemoButton.setOnClickListener(this);
        mPresenter.onCreateView();
        return root;
    }

    @Override
    public void setInfo(User user) {
        mTitleTextView.setText(getString(R.string.congrats, user.getName()));
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_payment_btn:{
                mPresenter.onSearchPokemonClicked();
                break;
            }
        }
    }
}
