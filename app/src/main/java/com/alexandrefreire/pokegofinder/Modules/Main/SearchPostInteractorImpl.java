package com.alexandrefreire.pokegofinder.Modules.Main;

import android.content.Context;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.Models.Post;
import com.alexandrefreire.pokegofinder.Modules.APIClient.GoFinderClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alexandre on 21/7/16.
 */
public class SearchPostInteractorImpl implements SearchPostInteractor {
    private Context mContext;

    public SearchPostInteractorImpl(Context context) {
        mContext = context;
    }


    @Override
    public void searchPokemon(double latitude, double longitude, final OnPostSearchFinishedListener listener) {
        GoFinderClient client = new GoFinderClient();
        PostService service = client.getRetrofit().create(PostService.class);
        Call call = service.searchPokemon(latitude, longitude);
        if (call != null){
            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (response.errorBody() != null){
                        try {
                            JSONObject json = new JSONObject(response.errorBody().string());
                            String msg = json.getString("error");
                            listener.onPokemonSearchError(msg);
                        } catch (JSONException e) {
                            listener.onPokemonSearchError( "");
                            e.printStackTrace();
                        } catch (IOException e) {
                            listener.onPokemonSearchError("");
                            e.printStackTrace();
                        }
                    }
                    else{
                        List<Post> pokemonList = response.body();
                        if (pokemonList != null){
                            listener.onPokemonSearchSuccess(pokemonList);
                        }
                        else{
                            listener.onPokemonSearchError("");
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    listener.onPokemonSearchError("");
                }
            });
        }
    }

    @Override
    public void searchPokemon(Pokemon pokemon, double latitude, double longitude, final OnPostSearchFinishedListener listener) {
        GoFinderClient client = new GoFinderClient();
        PostService service = client.getRetrofit().create(PostService.class);
        Call call = service.searchPokemon(pokemon.getPokedexIdentifier(), latitude, longitude);
        if (call != null){
            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (response.errorBody() != null){
                        try {
                            JSONObject json = new JSONObject(response.errorBody().string());
                            String msg = json.getString("error");
                            listener.onPokemonSearchError(msg);
                        } catch (JSONException e) {
                            listener.onPokemonSearchError( "");
                            e.printStackTrace();
                        } catch (IOException e) {
                            listener.onPokemonSearchError("");
                            e.printStackTrace();
                        }
                    }
                    else{
                        List<Post> pokemonList = response.body();
                        if (pokemonList != null){
                            listener.onPokemonSearchSuccess(pokemonList);
                        }
                        else{
                            listener.onPokemonSearchError("");
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    listener.onPokemonSearchError("");
                }
            });
        }
    }
}
