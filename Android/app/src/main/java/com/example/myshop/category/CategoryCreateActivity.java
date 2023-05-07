package com.example.myshop.category;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.example.myshop.BaseActivity;
import com.example.myshop.ChangeImageActivity;
import com.example.myshop.MainActivity;
import com.example.myshop.R;
import com.example.myshop.dto.category.CategoryCreateDTO;
import com.example.myshop.service.ApplicationNetwork;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryCreateActivity extends BaseActivity {

    private static int SELECT_IMAGE_RESULT=300;
    Uri uri = null;
    ImageView imgSelectImage;

    TextInputEditText txtCategoryName;
    TextInputEditText txtCategoryPriotity;
    TextInputEditText txtCategoryDescription;
    //Ініціалізуємо змінні
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_create);
        imgSelectImage=findViewById(R.id.imgSelectImage);
        txtCategoryName=findViewById(R.id.txtCategoryName);
        txtCategoryPriotity=findViewById(R.id.txtCategoryPriority);
        txtCategoryDescription=findViewById(R.id.txtCategoryDescription);
    }
    //action на кнопку вибору фотографії
    public void onClickSelectImage(View view) {
        //викликаємо вікно вибору фотографії
        Intent intent = new Intent(this, ChangeImageActivity.class);
        startActivityForResult(intent, SELECT_IMAGE_RESULT);
    }
    //отримуємо вибрану фотографію і встановлюємо її url
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_IMAGE_RESULT && data!=null) {
            uri = (Uri) data.getParcelableExtra("croppedUri");
            imgSelectImage.setImageURI(uri);
        }
    }
    //Створюємо нову категорію
    public void onClickSave(View view) {
        //Створюємо об'єкт та встановлюємо в нього значення
        CategoryCreateDTO model = new CategoryCreateDTO();
        model.setName(txtCategoryName.getText().toString());
        model.setPriority(Integer.parseInt(txtCategoryPriotity.getText().toString()));
        model.setDescription(txtCategoryDescription.getText().toString());
        model.setImageBase64(uriGetBase64(uri));
        //перевіряємо чи поля не пусті
        if(validate())
        {
            ApplicationNetwork.getInstance()
                    .getCategoriesApi()
                    .create(model)
                    .enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Intent intent = new Intent(CategoryCreateActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                        }
                    });
        }
    }
    //Метод валідації
    public boolean validate(){
        if(txtCategoryName.getText().toString().trim().isEmpty())
            return false;
        if (txtCategoryPriotity.getText().toString().trim().isEmpty())
            return false;
        if (txtCategoryDescription.getText().toString().trim().isEmpty())
            return false;
        if (uriGetBase64(uri).trim().isEmpty())
            return false;
        return  true;
    }
    //Метод перетворення uri в base64
    private String uriGetBase64(Uri uri) {
        try {
            Bitmap bitmap=null;
            //Перевіряємо чи вдається отриати зображення
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch(IOException e) {
                e.printStackTrace();
            }
            //зображення перетворюється в масив байтів
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            // масив байтів стискається у форматі JPEG
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            byte[] byteArr = bytes.toByteArray();
            return Base64.encodeToString(byteArr, Base64.DEFAULT);

        } catch(Exception ex) {
            return null;
        }
    }
}