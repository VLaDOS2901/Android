package com.example.myshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myshop.constants.Urls;
import com.example.myshop.models.Category;
import com.example.myshop.retrofit.NetworkService;
import com.example.myshop.services.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;
    private List<String> mData;
    private List<String> mImageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mData = new ArrayList<>();
        mImageUrls = new ArrayList<>();

        mAdapter = new CategoryAdapter(mData, mImageUrls);
        mRecyclerView.setAdapter(mAdapter);

        NetworkService.getInstance()
                .getJSONApi()
                .getList()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                        //ListView listView = findViewById(R.id.list_view);

                        List<Category> categories = response.body();
                        //List<String> names = new ArrayList<>();

                        for (Category item : categories){
                            //mAdapter.addData(item.getId() + ")\t" + item.getName());
//                            ImageView avatar = (ImageView)findViewById(R.id.myImage);

                            String url = Urls.BASE+item.getImage();
                            mAdapter.addData(item.getName(), url);


//                          System.out.println("/////////////////////////////////////////////////////////"+url);
//                            Glide.with(HomeApplication.getAppContext())
//                                    .load(url)
//                                    .apply(new RequestOptions().override(600))
//                                    .into(avatar);
                        }
                        //ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                        //        android.R.layout.simple_list_item_1, names);
                        //listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

    }


}