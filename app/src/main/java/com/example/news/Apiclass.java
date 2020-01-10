package com.example.news;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclass {
    private static final String BASE_URL="https://newsapi.org/v2/";
    private static Apiclass apiclass;
    private static Retrofit retrofit;

    private Apiclass(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    }
    public static synchronized Apiclass getInstance(){
        if(apiclass==null){
            apiclass=new Apiclass();
        }
        return apiclass;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
