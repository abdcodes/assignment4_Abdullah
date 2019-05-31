package com.example.assignmentAbdullah;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.assignmentAbdullah.Adapter.RecycleAdapter;
import com.example.assignmentAbdullah.INTERFACE_API.Item_Interface;
import com.example.assignmentAbdullah.Model_API.Item_Model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recycle;
    private List<Item_Model> list = new ArrayList<>();
    private Button btnAddItem;
    private Retrofit retrofit;
    private Item_Interface item_interface;
    Api_Connection api_connection;
    private RecycleAdapter adap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashbaord);
        recycle = findViewById(R.id.recycleView);
        btnAddItem = findViewById(R.id.btnaddItem);
        btnAddItem.setOnClickListener(this);
        api_connection = new Api_Connection();
        retrofit= api_connection.getRetrofit();
        item_interface = retrofit.create(Item_Interface.class);
        getData();
    }

    private void getData(){
        Call<List<Item_Model>> data = item_interface.getAllItems();
        data.enqueue(new Callback<List<Item_Model>>() {
            @Override
            public void onResponse(Call<List<Item_Model>> call, Response<List<Item_Model>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    adap = new RecycleAdapter(Dashboard.this, list);
                    recycle.setAdapter(adap);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycle.setLayoutManager(mLayoutManager);
                }
            }

            @Override
            public void onFailure(Call<List<Item_Model>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnaddItem){
            startActivity(new Intent(Dashboard.this,Item.class));
        }
    }
}
