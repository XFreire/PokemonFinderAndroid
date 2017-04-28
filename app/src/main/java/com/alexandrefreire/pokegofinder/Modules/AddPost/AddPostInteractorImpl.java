package com.alexandrefreire.pokegofinder.Modules.AddPost;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.Models.Post;
import com.alexandrefreire.pokegofinder.Models.User;
import com.alexandrefreire.pokegofinder.Modules.APIClient.GoFinderClient;
import com.alexandrefreire.pokegofinder.Modules.Main.PostService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alexandre on 13/8/16.
 */
public class AddPostInteractorImpl implements AddPostInteractor {
    @Override
    public void addPost(double lat, double lng, Pokemon pokemon, final AddPostFinishedListener listener) {
        GoFinderClient client = new GoFinderClient();
        PostService service = client.getRetrofit().create(PostService.class);
        Call call = service.addPost(lat, lng, pokemon.getPokedexIdentifier(), User.getCurrentUser().getIdentifier());
        if (call != null){
            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (response.errorBody() != null){
                        try {
                            JSONObject json = new JSONObject(response.errorBody().string());
                            String msg = json.getString("error");
                            listener.onAddPostError();
                        } catch (JSONException e) {
                            listener.onAddPostError();
                            e.printStackTrace();
                        } catch (IOException e) {
                            listener.onAddPostError();
                            e.printStackTrace();
                        }
                    }
                    else{
                        Post post = response.body();
                        if (post != null){
                            listener.onAddPostSuccess(post);
                        }
                        else{
                            listener.onAddPostError();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    listener.onAddPostError();
                }
            });
        }
    }
}
