package com.example.myshop.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myshop.R;
import com.example.myshop.application.HomeApplication;
import com.example.myshop.models.constants.Urls;
import com.example.myshop.dto.category.CategoryItemDTO;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryCardViewHolder> {

    private List<CategoryItemDTO> categories;
    private final OnCategoryClickListener onClickDeleteCategory;

    public CategoriesAdapter(List<CategoryItemDTO> categories,
                             OnCategoryClickListener onClickDeleteCategory) {
        this.categories = categories;
        this.onClickDeleteCategory = onClickDeleteCategory;
    }
    //створюємо і повертаємо новий об'єкт CategoryCardViewHolder
    @NonNull
    @Override
    public CategoryCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_view, parent, false);
        return new CategoryCardViewHolder(view);
    }
    //заповнюємо дані в CategoryCardViewHolder
    @Override
    public void onBindViewHolder(@NonNull CategoryCardViewHolder holder, int position) {
        if(categories!=null && position<categories.size())
        {
            CategoryItemDTO item = categories.get(position);
            holder.getCategoryName().setText(item.getName());
            String url = Urls.BASE+item.getImage();
            Glide.with(HomeApplication.getAppContext())
                    .load(url)
                    .apply(new RequestOptions().override(600))
                    .into(holder.getCategoryImage());

            holder.getBtnDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickDeleteCategory.OnButtonClick(item);
                }
            });
        }
    }
    //повертаємо кількість елементів у списку категорій.
    @Override
    public int getItemCount() {
        return categories.size();
    }
}