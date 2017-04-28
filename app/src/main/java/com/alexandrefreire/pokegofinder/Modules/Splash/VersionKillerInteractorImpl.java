package com.alexandrefreire.pokegofinder.Modules.Splash;

import android.content.Context;

import com.alexandrefreire.pokegofinder.Modules.APIClient.GoFinderClient;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alexandre on 21/7/16.
 */
public class VersionKillerInteractorImpl implements VersionKillerInteractor{
    private Context mContext;

    public VersionKillerInteractorImpl(Context context){
        mContext = context;
    }
    @Override
    public void getCurrentVersion(final OnVersionKillerFinishedListener listener) {
        GoFinderClient client = new GoFinderClient();
        VersionKillerService service = client.getRetrofit().create(VersionKillerService.class);
        Call call = service.version();
        if (call != null){
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.errorBody() != null){
                        try {
                            JSONObject json = new JSONObject(response.errorBody().string());
                            String msg = json.getString("error");
                            listener.onVersionKillerError(msg);
                        } catch (JSONException e) {
                            listener.onVersionKillerError( "");
                            e.printStackTrace();
                        } catch (IOException e) {
                            listener.onVersionKillerError("");
                            e.printStackTrace();
                        }
                    }
                    else{
                        JsonObject json = response.body();
                        if (json != null){
                            int versionCode = json.get("android_version").getAsInt();
                            boolean enabled = json.get("force_android").getAsBoolean();
                            listener.onVersionKillerSuccess(versionCode, enabled);
                        }
                        else{
                            listener.onVersionKillerError("");
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    listener.onVersionKillerError("");
                }
            });
        }
    }
}
