package com.alexandrefreire.pokegofinder.Modules.AddPost;

import com.alexandrefreire.pokegofinder.Models.Pokemon;

/**
 * Created by Alexandre on 29/7/16.
 */
public interface AddPostPresenter {
    void onCreateView();
    void keyboardVisible(boolean isKeyboardVisible);
    void onNameChanged(String s);
    void onPokemonSelected(Pokemon pokemon);
    void onNextClicked();
    void onPreviousClicked();
}
