package com.example.splash;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder>{


    List<Item> items;
    Context context;

    public CustomAdapter(Context context, List<Item> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new Holder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Item i = items.get(position);
        holder.text.setText(i.getTitle());
        holder.body.setText(i.getContent());


       Document document = Jsoup.parse(i.getContent());
        holder.body.setText(document.text());

       Elements elements = document.select("img");
        Log.d("CODE","IMAGE  - "+  elements.get(0).attr("src"));


        Glide
                .with(context)
                .load(elements.get(0).attr("src"))
                .into(holder.image);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity3.class);
                intent.putExtra("url",i.getUrl());
                context.startActivity(intent);
            }
        });
        

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView text;
        public TextView body;
        public Holder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.image);
            text = (TextView)itemView.findViewById(R.id.text_title);
            body = (TextView)itemView.findViewById(R.id.text_body);
        }
    }
}
