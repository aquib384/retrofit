package com.firstapp.retrodemo;

import com.firstapp.retrodemo.model.User;

import retrofit2.Call;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {


    @POST("api/v1/launchData/")
    Call<User> getFilterName();


}
