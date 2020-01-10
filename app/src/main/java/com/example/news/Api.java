package com.example.news;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL="https://newsapi.org/v2/";
    @GET("top-headlines")
    Call<List<Newsclass>> getNews(
            @Query("country")String country,
            @Query("apiKey")String apiKey
    );
}
