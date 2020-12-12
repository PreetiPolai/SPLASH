package com.example.splash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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


        Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher m = p.matcher(i.getContent());
        List<String> tokens = new ArrayList<>();
        while (m.find()){
            String token = m.group();
            tokens.add(token);
        }


        Glide
                .with(context)
                .load(tokens.get(0))
                .centerCrop()
                .into(holder.image);


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
