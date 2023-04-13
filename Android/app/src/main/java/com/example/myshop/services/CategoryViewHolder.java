package com.example.myshop.services;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myshop.R;

// MyViewHolder.java
public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private ImageView imageView;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textview_item);
        imageView = itemView.findViewById(R.id.imageview_item);
    }

    public void bindData(String text, String imageUrl) {
        textView.setText(text);
        Glide.with(itemView.getContext())
                .load(imageUrl)
                .into(imageView);
    }

}

