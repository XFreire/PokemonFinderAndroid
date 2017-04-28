package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Password;

import com.alexandrefreire.pokegofinder.Models.User;
import com.alexandrefreire.pokegofinder.Modules.APIClient.GoFinderClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Alexandre on 20/2/16.
 */
public class UserInteractorImpl implements UserInteractor {
    @Override
    public void createUser(final String name, final String email, final String password, final OnUserFinishedListener listener) {
        GoFinderClient client = new GoFinderClient();
        UserService service = client.getRetrofit().create(UserService.class);
        Call call = service.signUp(name, email, password);
        if (call != null){
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.errorBody() != null){
                        try {
                            JSONObject json = new JSONObject(response.errorBody().string());
                            String msg = json.getString("error");
                            listener.onUsernameError();
                        } catch (JSONException e) {
                            listener.onUsernameError();
                            e.printStackTrace();
                        } catch (IOException e) {
                            listener.onUsernameError();
                            e.printStackTrace();
                        }
                    }
                    else{
                        User user = response.body();
                        if (user != null){
                            listener.onSuccess(user);
                        }
                        else{
                            listener.onUsernameError();
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    listener.onUsernameError();
                }
            });
        }
    }

    @Override
    public void signIn(String email, String password, final OnUserFinishedListener listener) {
        GoFinderClient client = new GoFinderClient();
        UserService service = client.getRetrofit().create(UserService.class);
        Call call = service.signIn(email, password);
        if (call != null){
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.errorBody() != null){
                        try {
                            JSONObject json = new JSONObject(response.errorBody().string());
                            String msg = json.getString("error");
                            listener.onUsernameError();
                        } catch (JSONException e) {
                            listener.onUsernameError();
                            e.printStackTrace();
                        } catch (IOException e) {
                            listener.onUsernameError();
                            e.printStackTrace();
                        }
                    }
                    else{
                        User user = response.body();
                        if (user != null){
                            listener.onSuccess(user);
                        }
                        else{
                            listener.onUsernameError();
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    listener.onUsernameError();
                }
            });
        }
    }
}
