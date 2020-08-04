package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private Context context;
    private OnItemClickListener listener;
    private List<Articles> articles;
    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }
    public void setOnItemClickListener(OnItemClickListener listener){this.listener=listener;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_cards,parent,false);
        return new ViewHolder(view,listener);
    }
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles a=articles.get(position);
        holder.title.setText(a.getTitle());
        holder.description.setText(a.getDescription());
        //holder.url.setText(a.getUrl());
        String urlToImage=a.getUrlToImage();
        Picasso.get().load(urlToImage).into(holder.pic);

        

    }
    @Override
    public int getItemCount() {
        return articles.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,description,url;
        ImageView pic;
        CardView cardView;
        public ViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            //url=itemView.findViewById(R.id.url);
            pic=itemView.findViewById(R.id.urlimage);
            cardView=itemView.findViewById(R.id.cardView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });




        }
    }


}

