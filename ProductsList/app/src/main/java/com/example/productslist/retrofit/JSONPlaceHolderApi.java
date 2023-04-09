package com.example.productslist.retrofit;

import com.example.productslist.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {
    @GET("/posts/{id}")
    public Call<Product> getPostWithID(@Path("id") int id);

    @GET("/api/products/list")
    public Call<List<Product>> getList();
}
