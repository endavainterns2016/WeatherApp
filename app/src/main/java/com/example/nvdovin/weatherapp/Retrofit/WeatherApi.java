package com.example.nvdovin.weatherapp.Retrofit;

import com.example.nvdovin.weatherapp.model.FirstModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherApi {

    @GET("forecast")
    Call<FirstModel> getResponse(@Query("appid") String API_KEY, @Query("q") String query_city_name);

}
