package com.example.assignmentAbdullah;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Description extends AppCompatActivity {
    private TextView itemName, itemPrice, ItemDesc;
    private ImageView imgV;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.description);
        itemName = findViewById(R.id.tvItemName);
        itemPrice = findViewById(R.id.tvprice);
        ItemDesc  = findViewById(R.id.tvDesc);
        imgV = findViewById(R.id.imageView3);

        if(getIntent() != null){
            Toast.makeText(this, "Got some data", Toast.LENGTH_SHORT).show();
            itemName.setText(getIntent().getStringExtra("name"));
            itemPrice.setText(getIntent().getStringExtra("price"));
            ItemDesc.setText(getIntent().getStringExtra("description"));
            String image_url = getIntent().getStringExtra("itemImageName");
            try {
                URL url = new URL(image_url);
                imgV.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
