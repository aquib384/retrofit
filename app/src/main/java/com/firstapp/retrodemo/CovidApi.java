package com.firstapp.retrodemo;

import com.firstapp.retrodemo.model.State;
import com.firstapp.retrodemo.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CovidApi {


    @GET("key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true")
    Call<State> getUser();


}
