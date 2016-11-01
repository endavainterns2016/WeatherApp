package com.example.nvdovin.weatherapp.data.network.api;

import com.example.nvdovin.weatherapp.data.network.response.GetCityListResponse;
import com.example.nvdovin.weatherapp.data.model.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface WeatherApi {

    String FIVE_DAY_FORECAST = "/data/2.5/forecast";
    String APPID = "appid";
    String CITY_NAME = "q";

    @GET(FIVE_DAY_FORECAST)
    Call<City> getWeatherData(@Query(APPID) String API_KEY, @Query(CITY_NAME) String cityName);

    @GET
    Call<GetCityListResponse> getCities(@Url String url);
}
