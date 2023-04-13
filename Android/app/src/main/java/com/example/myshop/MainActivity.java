package com.example.myshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myshop.application.HomeApplication;
import com.example.myshop.constants.Urls;
import com.example.myshop.retrofit.Category;
import com.example.myshop.retrofit.NetworkService;

import java.util.ArrayList;
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
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                        ListView listView = findViewById(R.id.list_view);
                        List<Category> categories = response.body();
                        List<String> names = new ArrayList<>();

                        for (Category item : categories){
                            names.add(item.getId() + ")\t" + item.getName());
//                            ImageView avatar = (ImageView)findViewById(R.id.myImage);
//                            String url = Urls.BASE+item.getImage();
                          System.out.println("/////////////////////////////////////////////////////////"+names);
//                            Glide.with(HomeApplication.getAppContext())
//                                    .load(url)
//                                    .apply(new RequestOptions().override(600))
//                                    .into(avatar);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                                android.R.layout.simple_list_item_1, names);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }


}