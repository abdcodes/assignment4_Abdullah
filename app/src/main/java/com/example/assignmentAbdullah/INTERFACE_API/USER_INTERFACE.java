package com.example.assignmentAbdullah.INTERFACE_API;

import com.example.assignmentAbdullah.Model_API.USER;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface USER_INTERFACE {
    @POST("signup")
    Call<Void> registerUser(@Body USER user);

    @POST("login")
    Call<Void> loginUser(@Body USER user);
}
