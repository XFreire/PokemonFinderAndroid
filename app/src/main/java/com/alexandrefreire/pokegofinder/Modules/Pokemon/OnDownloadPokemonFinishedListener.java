package com.alexandrefreire.pokegofinder.Modules.Pokemon;

import com.alexandrefreire.pokegofinder.Models.Pokemon;

import java.util.List;

/**
 * Created by Alexandre on 23/7/16.
 */
public interface OnDownloadPokemonFinishedListener {
    void onDownloadPokemonSuccess(List<Pokemon> list);
    void onDownloadPokemonError(String error);
}
