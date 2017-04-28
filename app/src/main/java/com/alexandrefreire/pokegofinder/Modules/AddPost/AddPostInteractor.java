package com.alexandrefreire.pokegofinder.Modules.AddPost;

import com.alexandrefreire.pokegofinder.Models.Pokemon;

/**
 * Created by Alexandre on 13/8/16.
 */
public interface AddPostInteractor {
    void addPost(double lat, double lng, Pokemon pokemon, AddPostFinishedListener listener);
}
