package com.alexandrefreire.pokegofinder.Modules.Authentication.SignUp.Password;

import com.alexandrefreire.pokegofinder.Models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Alexandre on 21/7/16.
 */
public interface UserService {
    @FormUrlEncoded
    @POST("users/create.json")
    Call<User> signUp(@Field("user[name]")String  name,
                      @Field("user[email]")String  email,
                      @Field("user[password")String  password);

    @FormUrlEncoded
    @POST("users/signin.json")
    Call<User> signIn(@Field("user[email]")String  email,
                      @Field("user[password")String  password);
}
