package com.alexandrefreire.pokegofinder.Modules.APIClient;

import com.alexandrefreire.pokegofinder.Models.Deserializers.OneObjectDeserializer;
import com.alexandrefreire.pokegofinder.Models.Post;
import com.alexandrefreire.pokegofinder.Models.User;
import com.alexandrefreire.pokegofinder.Utils.Settings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alexandre on 21/7/16.
 */
public class GoFinderClient {
    private static final String BASE_URL_LOCALHOST = "http://192.168.0.160:3000/api/v2/";
    private static final  String BASE_URL_PRODUCTION = "https://poke-gofinder.herokuapp.com/api/v1/";

    public Retrofit mRetrofit = null;


    public GoFinderClient(){




        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request newRequest = chain.request();
                                User user = User.getCurrentUser();
                                if (user != null) {
                                    newRequest = chain.request().newBuilder()
                                            //.addHeader("Authentication", "Token token="+user.getApiKey())
                                            .header("Authorization", "Token token="+user.getApiKey())
                                            .build();
                                }
                                Response response = chain.proceed(newRequest);
                                return response;
                            }
                        })
                .build();


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(User.class,new OneObjectDeserializer<User>())
                .registerTypeAdapter(Post.class,new OneObjectDeserializer<Post>())
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(getEndPointUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
    public Retrofit getRetrofit(){
        return mRetrofit;
    }

    public static String getEndPointUrl() {
        if (Settings.ENVIROMENT == Settings.Environment.PRODUCTION){
            return BASE_URL_PRODUCTION;
        }
        else if (Settings.ENVIROMENT == Settings.Environment.LOCALHOST){
            return BASE_URL_LOCALHOST;
        }
        else {
            return BASE_URL_LOCALHOST;
        }
    }

}
