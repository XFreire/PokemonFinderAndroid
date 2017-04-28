package com.alexandrefreire.pokegofinder.Modules.AddPost;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.Modules.SearchPokemon.PokemonArrayAdapter;
import com.alexandrefreire.pokegofinder.R;
import com.alexandrefreire.pokegofinder.Utils.TextWatcher.MyTextWatcher;
import com.alexandrefreire.pokegofinder.Utils.TextWatcher.MyTextWatcherImpl;

import java.util.List;

public class AddPostFragment extends Fragment implements AddPostView, View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener, MyTextWatcher, AdapterView.OnItemClickListener {
    public static final String POKEMON_EXTRA = "AddPostFragment.POKEMON";
    private Context mContext;
    private AddPostPresenter mPresenter;

    private View mRoot;
    private AutoCompleteTextView mPokemonAutocomplete;
    private Button mOkButton;
    private ImageView mKeyboardImage;
    private ProgressBar mProgress;
    //private ImageButton mNextButton;
    //private ImageButton mPreviousButton;
    private ViewGroup mNavigationLayout;
    private ViewGroup mContentLayout;
    private PokemonArrayAdapter mPokemonAdapter;

    public AddPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRoot =  inflater.inflate(R.layout.fragment_add_post, container, false);
        mContext = getActivity();
        mPresenter = new AddPostPresenterImpl(mContext, this);
        mPokemonAutocomplete = (AutoCompleteTextView) mRoot.findViewById(R.id.pokemon_name_autocompletetextview);
        mOkButton = (Button) mRoot.findViewById(R.id.ok_btn);
        mKeyboardImage = (ImageView) mRoot.findViewById(R.id.keyboard_img);
        mProgress = (ProgressBar) mRoot.findViewById(R.id.progressBar);
        //mNextButton = (ImageButton) mRoot.findViewById(R.id.next_btn);
        //mPreviousButton = (ImageButton) mRoot.findViewById(R.id.previous_btn);
        mNavigationLayout = (ViewGroup) mRoot.findViewById(R.id.navigation_layout);
        mContentLayout = (ViewGroup) mRoot.findViewById(R.id.content_layout);
        MyTextWatcherImpl nameWatcher = new MyTextWatcherImpl(mPokemonAutocomplete, this);
        mPokemonAutocomplete.addTextChangedListener(nameWatcher);
        mPokemonAutocomplete.setOnClickListener(this);
        //mPreviousButton.setEnabled(false);
        mOkButton.setOnClickListener(this);
        //mPreviousButton.setOnClickListener(this);
        //mNextButton.setOnClickListener(this);
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
    public void setPokemonListInfo(List<Pokemon> list) {
        mPokemonAdapter = new PokemonArrayAdapter(mContext, R.layout.pokemon_listitem, list);
        mPokemonAutocomplete.setAdapter(mPokemonAdapter);
        mPokemonAutocomplete.setOnClickListener(this);
        mPokemonAutocomplete.setOnItemClickListener(this);
    }

    @Override
    public void showOkButton() {
        mPokemonAutocomplete.setCursorVisible(true);
        mOkButton.setVisibility(View.VISIBLE);
        mKeyboardImage.setVisibility(View.GONE);
    }

    @Override
    public void hideOkButton() {
        mPokemonAutocomplete.setCursorVisible(false);
        mOkButton.setVisibility(View.GONE);
        mKeyboardImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void enableOkButton() {
        mOkButton.setEnabled(true);
        //mNextButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_white_24dp));
        //mNextButton.setEnabled(true);
    }

    @Override
    public void disableOkButton() {
        mOkButton.setEnabled(false);
        //mNextButton.setEnabled(false);
        //mNextButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_blue_24dp));
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
    public void navigateToMain() {
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_btn:
            case R.id.next_btn:{
                mPresenter.onNextClicked();
                break;
            }
            case R.id.pokemon_name_autocompletetextview:{
                showOkButton();
                break;
            }

        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Pokemon pokemon = mPokemonAdapter.getItem(position);
        mPresenter.onPokemonSelected(pokemon);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideKeyboard();
            }
        }, 500);

    }

    @Override
    public void onGlobalLayout() {
        int heightDiff = mRoot.getRootView().getHeight() - mRoot.getHeight();
        if (heightDiff > 600) { // if more than 600 pixels, its probably a keyboard...
            mPresenter.keyboardVisible(true);
        }
        else{
            if (mPokemonAutocomplete.getText().length() > 0){
                mPresenter.keyboardVisible(true);
            }
            else{
                mPresenter.keyboardVisible(false);
            }
        }
    }

    @Override
    public void onTextChanged(View view, CharSequence s, int start, int before, int count) {
        mPresenter.onNameChanged(s.toString());
    }


}
