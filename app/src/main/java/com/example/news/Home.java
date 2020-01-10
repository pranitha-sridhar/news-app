package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity {

    Retrofit retrofit;
    Api api;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Articles> articles=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        retrieveJSON("in","8d4405efb30c405c8bd8ae6c449e5078");


        retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        api = retrofit.create(Api.class);
        recyclerView=findViewById(R.id.RView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public void retrieveJSON(String country, String APIKEY){
        Call<Newsclass> call =api.getNews(country,APIKEY);
        call.enqueue(new Callback <Newsclass>() {
            @Override
            public void onResponse(Call<List<Articles>> call, Response<List<Articles>> response) {
                articles=response.body().getArticles();
                adapter=new Adapter(Home.this,articles);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Newsclass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}




