package com.example.assignmentAbdullah;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignmentAbdullah.INTERFACE_API.USER_INTERFACE;
import com.example.assignmentAbdullah.Model_API.USER;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button btnlogin, btnS;
    private Retrofit retrofit;
    private USER_INTERFACE user_interface;
    private  Api_Connection api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
        api = new Api_Connection();
        getConnection();
    }

    private void getConnection() {
        retrofit =api.getRetrofit();
        user_interface = api.getUser_interface();
    }

    private void init() {
        username = findViewById(R.id.log_username);
        password = findViewById(R.id.log_password);
        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);
        btnS = findViewById(R.id.button2);
        btnS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnlogin){
            if(TextUtils.isEmpty(username.getText())){
                username.setError("Please Enter Username");
                return;
            }
            if(TextUtils.isEmpty(password.getText())){
                password.setError("Please Enter Username");
                return;
            }
            AuthenticateUser(username.getText().toString(), password.getText().toString());
        }else if(v.getId() == R.id.button2){
            startActivity(new Intent(Login.this,Signup.class));
        }
    }

    private void AuthenticateUser(String username, String password) {
        USER user = new USER(username, password);
        Call<Void> call = user_interface.loginUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(Login.this, "Logged In Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this,Dashboard.class));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Login.this, "failed", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
