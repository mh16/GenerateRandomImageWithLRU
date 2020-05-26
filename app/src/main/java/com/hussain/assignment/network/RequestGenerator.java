package com.hussain.assignment.network;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestGenerator {
    private static final String TAG = "RequestGenerator";


    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";
    //https://dog.ceo/api/breeds/image/random


    private Retrofit mRetrofit;
    private static volatile RequestGenerator mInstance;

    public static RequestGenerator getInstance() {

        if (mInstance == null) {
            synchronized (RequestGenerator.class) {
                if (mInstance == null) {
                    mInstance = new RequestGenerator();
                }
            }
        }
        return mInstance;
    }

    @NonNull
    public ApiService create() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            Log.e(TAG, mRetrofit.baseUrl().toString());
        }

        return mRetrofit.create(ApiService.class);
    }
}
