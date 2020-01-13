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
    RecyclerView recyclerView;
    Adapter adapter;
    List<Articles> articles=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        retrieveJSON("in","8d4405efb30c405c8bd8ae6c449e5078");

        recyclerView=findViewById(R.id.RView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void retrieveJSON(String country, String API_KEY){
        Api api=Apiclass.getApiClass().create(Api.class);

        Call<Newsclass> call =api.getNews(country,API_KEY);
        call.enqueue(new Callback<Newsclass>(){
            @Override
            public void onResponse(Call<Newsclass> call, Response<Newsclass> response) {
                if(response.isSuccessful() && response.body().getArticles()!=null){
                    if(!articles.isEmpty()) articles.clear();
                    articles=response.body().getArticles();

                    adapter=new Adapter(Home.this,articles);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(),"ERROR!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Newsclass> call, Throwable t) { }

        });

    }
}




