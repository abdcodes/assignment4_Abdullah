package com.example.assignmentAbdullah.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignmentAbdullah.Api_Connection;
import com.example.assignmentAbdullah.Description;
import com.example.assignmentAbdullah.Model_API.Item_Model;
import com.example.assignmentAbdullah.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RecycleAdapter  extends RecyclerView.Adapter<RecycleAdapter.ViewHold>{
    private Context context;
    private List<Item_Model> list;

    public RecycleAdapter(Context context, List<Item_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cutom_layout_for_recycleview,parent,false);
        return  new ViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold v, int i) {
        final String name = list.get(i).getItemName();
        final String price=list.get(i).getItemPrice();
        final String description=list.get(i).getItemDescription();
        final String path=list.get(i).getImagePath();
        final String itemImageName=list.get(i).getItemImageName();


        v.itemName.setText("Item Name :"+list.get(i).getItemName());
        v.itemPrice.setText("Item Price :"+list.get(i).getItemPrice());
        v.itemDescription.setText("Item Description :"+list.get(i).getItemDescription());
        final String image_url = Api_Connection.API_URL+"Images/upload/"+list.get(i).getItemImageName();
        System.out.println(list.get(i).getItemImageName());
        strictMode();
        try {
            URL url = new URL(image_url);
            v.img.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        v.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Description.class);
                intent.putExtra("name",name);
                intent.putExtra("price",price);
                intent.putExtra("description",description);
                intent.putExtra("path",path);
                intent.putExtra("itemImageName",image_url);
                context.startActivity(intent);
            }
        });

    }

    private void strictMode() {
        StrictMode.ThreadPolicy stict =new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(stict);
    }

    @Override
    public int getItemCount() {
        return  list.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder{
        private TextView itemName, itemPrice, itemDescription;
        private ImageView img;
        public ViewHold(@NonNull View v) {
            super(v);
            itemName = v.findViewById(R.id.custom_item_name);
            itemPrice = v.findViewById(R.id.custom_item_price);
            itemDescription = v.findViewById(R.id.custom_item_description);
            img = v.findViewById(R.id.imageView2);
        }
    }
}
