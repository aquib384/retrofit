package com.firstapp.retrodemo;

import com.firstapp.retrodemo.model.FilterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {


    @POST("api/v1/launchData/")
    Call<User> getFilterName();


}
