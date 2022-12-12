package com.example.examws;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
    @POST("user/login")
    Call<LoginResponse> login(@Body LoginModel loginModel);
}
