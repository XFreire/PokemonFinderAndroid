package com.alexandrefreire.pokegofinder.Modules.AddPost;

import com.alexandrefreire.pokegofinder.Models.Pokemon;

import java.util.List;

/**
 * Created by Alexandre on 29/7/16.
 */
public interface AddPostView {
    void setProgress(int progress);
    void hideKeyboard();
    void setPokemonListInfo(List<Pokemon> list);
    void showOkButton();
    void hideOkButton();
    void enableOkButton();
    void disableOkButton();
    void showNavigationLayout();
    void hideNavigationLayout();
    void navigateToMain();
}
