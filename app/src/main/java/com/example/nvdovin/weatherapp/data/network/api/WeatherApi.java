package com.example.nvdovin.weatherapp.data.network.api;

import com.example.nvdovin.weatherapp.data.network.response.CityListResponse;
import com.example.nvdovin.weatherapp.data.model.City;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;


public interface WeatherApi {

    String FIVE_DAY_FORECAST = "/data/2.5/forecast";
    String APPID = "appid";
    String CITY_NAME = "q";

    @GET(FIVE_DAY_FORECAST)
    Observable<City> getWeatherData(@Query(APPID) String API_KEY, @Query(CITY_NAME) String cityName);

    @GET
    Observable<CityListResponse> getCities(@Url String url);
}
