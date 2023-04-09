package com.example.myshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myshop.application.HomeApplication;
import com.example.myshop.constants.Urls;
import com.example.myshop.retrofit.JSONPlaceHolderApi;
import com.example.myshop.retrofit.NetworkService;
import com.example.myshop.retrofit.Post;

import java.io.Console;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkService.getInstance()
                .getJSONApi()
                .getList()
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                        List<Post> posts = response.body();
                        for (Post item : posts){
                            ImageView avatar = (ImageView)findViewById(R.id.myImage);
                            String url = Urls.BASE+item.getImage();
                            System.out.println("/////////////////////////////////////////////////////////"+url);
                            Glide.with(HomeApplication.getAppContext())
                                    .load(url)
                                    .apply(new RequestOptions().override(600))
                                    .into(avatar);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }


}