package com.example.news;

import com.google.android.gms.common.api.GoogleApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("top-headlines")
    Call<List<Newsclass>> getNews(
            @Query("country")String country,
            @Query("apiKey")String apiKey
    );


}
