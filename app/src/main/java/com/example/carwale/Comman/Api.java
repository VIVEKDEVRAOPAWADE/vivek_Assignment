package com.example.carwale.Comman;



import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
//https://api.covid19api.com/summary
    String BASE_URL = "https://api.covid19api.com/";

    @GET("summary")
    Call<String> getList();
}
