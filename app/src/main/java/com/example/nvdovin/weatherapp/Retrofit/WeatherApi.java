package com.example.nvdovin.weatherapp.retrofit;

import com.example.nvdovin.weatherapp.model.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherApi {

    String FIVE_DAY_FORECAST = "/data/2.5/forecast";
    String APPID = "appid";
    String CITY_NAME = "q";

    @GET(FIVE_DAY_FORECAST)
    Call<Forecast> getWeatherData(@Query(APPID) String API_KEY, @Query(CITY_NAME) String cityName);

}
