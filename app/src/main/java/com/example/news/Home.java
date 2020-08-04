package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    List<Articles> articles=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView=findViewById(R.id.RView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        retrieveJSON("in","8d4405efb30c405c8bd8ae6c449e5078");



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
                    adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Toast.makeText(Home.this, "Open", Toast.LENGTH_SHORT).show();
                            Articles art=articles.get(position);
                            String url=art.getUrl();
                            Intent intent=new Intent(Home.this,WebUrl.class);
                            intent.putExtra("url",url);
                            startActivity(intent);

                        }
                    });
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




