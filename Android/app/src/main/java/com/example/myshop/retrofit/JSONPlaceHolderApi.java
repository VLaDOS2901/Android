package com.example.myshop.retrofit;

import com.example.myshop.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET("/categories/{id}")
    public Call<Category> getPostWithID(@Path("id") int id);

    @GET("/api/categories/list")
    public Call<List<Category>> getList();
}
