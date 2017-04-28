package com.alexandrefreire.pokegofinder.Modules.AddPost;

import com.alexandrefreire.pokegofinder.Models.Post;

/**
 * Created by Alexandre on 13/8/16.
 */
public interface AddPostFinishedListener {
    void onAddPostSuccess(Post post);
    void onAddPostError();
}
