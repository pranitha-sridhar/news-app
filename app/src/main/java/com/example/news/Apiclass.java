package com.example.news;

import okhttp3.OkHttpClient;
import okhttp3.internal.tls.OkHostnameVerifier;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclass {
    public static final String BASE_URL="https://newsapi.org/v2/";
    public static Retrofit retrofit;
    public static Retrofit getApiClass(){
        if(retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }





}
