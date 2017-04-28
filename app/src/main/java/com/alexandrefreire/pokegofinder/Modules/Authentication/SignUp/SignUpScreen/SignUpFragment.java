package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.SignUpScreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Email.EmailActivity;
import com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Name.NameActivity;
import com.alexandrefreire.pokegofinder.R;


public class SignUpFragment extends Fragment implements View.OnClickListener {
    private Button mSignUpButton;
    private TextView mSignInButton;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);
        mSignUpButton = (Button) root.findViewById(R.id.sign_up_btn);
        mSignInButton = (TextView) root.findViewById(R.id.sign_in_btn);
        mSignUpButton.setOnClickListener(this);
        mSignInButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_up_btn:{
                navigateToName();
                break;
            }
            case R.id.sign_in_btn:{
                navigateToSignIn();
                break;
            }
        }
    }

    private void navigateToSignIn() {
        Intent intent = new Intent(getActivity(), EmailActivity.class);
        startActivity(intent);
    }

    private void navigateToName() {
        Intent intent = new Intent(getActivity(), NameActivity.class);
        startActivity(intent);
    }
}
