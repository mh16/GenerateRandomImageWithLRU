package com.hussain.assignment.network;

import com.hussain.assignment.model.Dogs;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    @GET("/")
    Call<Dogs> getRandomDogs();
}
