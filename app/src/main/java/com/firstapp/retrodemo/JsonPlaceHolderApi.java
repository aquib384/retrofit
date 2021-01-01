package com.firstapp.retrodemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @POST("api/v1/launchData/")
    Call<FilterName> getFilterName();


}
