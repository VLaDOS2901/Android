package com.example.myshop.services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.R;

import java.util.List;

// MyAdapter.java
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<String> mData;
    private List<String> mImageUrls;

    public CategoryAdapter(List<String> data, List<String> imageUrls) {
        mData = data;
        mImageUrls = imageUrls;
    }

    public void addData(String text, String imageUrl) {
        mData.add(text);
        mImageUrls.add(imageUrl);
        notifyItemInserted(mData.size() - 1);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        String text = mData.get(position);
        String imageUrl = mImageUrls.get(position);
        holder.bindData(text, imageUrl);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}

