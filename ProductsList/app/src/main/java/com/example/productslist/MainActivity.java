package com.example.productslist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.productslist.constants.Urls;
import com.example.productslist.models.Product;
import com.example.productslist.retrofit.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("////////////////////////////////////////////////////");
        NetworkService.getInstance()
                .getJSONApi()
                .getList()
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                        System.out.println(response+"////////////////////////////////////////////////////");
                        List<Product> products = response.body();
                        LinearLayout linearLayout = findViewById(R.id.linearLayout);
                        for (Product item : products){
                            TextView textView = new TextView(getBaseContext()); // створити новий об'єкт TextView
                            System.out.println(item.getId() + "\t" + item.getName() + "\t" + item.getPrice());
                            textView.setText(item.getId() + "\t" + item.getName() + "\t" + item.getPrice()); // встановити текст для TextView
                            linearLayout.addView(textView); // додати TextView до LinearLayout
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }
}