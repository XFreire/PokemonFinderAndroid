package com.alexandrefreire.pokegofinder.Utils.TextWatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

/**
 * Created by Alexandre on 2/11/15.
 */
public class MyTextWatcherImpl implements TextWatcher {
    private MyTextWatcher mTextWatcher;
    private View mView;

    public MyTextWatcherImpl(View view, MyTextWatcher textWatcher) {
        mView = view;
        mTextWatcher = textWatcher;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mTextWatcher.onTextChanged(mView, s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

