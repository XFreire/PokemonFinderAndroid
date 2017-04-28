package com.alexandrefreire.pokegofinder.Modules.Pokemon;

import android.content.Context;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.Modules.APIClient.GoFinderClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alexandre on 23/7/16.
 */
public class PokemonInteractorImpl implements PokemonInteractor {
    private Context mContext;
    public void PokemonInteractorImpl(){

    }
    @Override
    public void getPokemons(final OnDownloadPokemonFinishedListener listener) {
        GoFinderClient client = new GoFinderClient();
        PokemonService service = client.getRetrofit().create(PokemonService.class);
        Call call = service.downloadPokemons();
        if (call != null){
            call.enqueue(new Callback<List<Pokemon>>() {
                @Override
                public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                    if (response.errorBody() != null){
                        try {
                            JSONObject json = new JSONObject(response.errorBody().string());
                            listener.onDownloadPokemonError("");
                        } catch (JSONException e) {
                            listener.onDownloadPokemonError("");
                            e.printStackTrace();
                        } catch (IOException e) {
                            listener.onDownloadPokemonError("");
                            e.printStackTrace();
                        }
                    }
                    else{
                        List<Pokemon> pokemonList = response.body();
                        if (pokemonList != null){
                            listener.onDownloadPokemonSuccess(pokemonList);
                        }
                        else{
                            listener.onDownloadPokemonError("");
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                    listener.onDownloadPokemonError("");
                }
            });
        }
    }
}
