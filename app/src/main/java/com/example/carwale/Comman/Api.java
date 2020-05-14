package com.example.carwale.Comman;

import com.example.carwale.model.ListFields;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
//https://api.covid19api.com/summary
    String BASE_URL = "https://api.covid19api.com/";

    @GET("summary")
    Call<String> getList();
}
