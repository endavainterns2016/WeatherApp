package com.example.nvdovin.weatherapp.factory;

import android.util.Log;

import com.example.nvdovin.weatherapp.Retrofit.RetrofitInterfaceAPI;
import com.example.nvdovin.weatherapp.Retrofit.RetrofitResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mcebotari on 10/24/2016.
 */

public class RetrofitFactory {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "94e17e8755c5bc98caaf0a25e9c15d3f";
    public RetrofitFactory(String query_city_name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterfaceAPI retrofitInterfaceAPI = retrofit.create(RetrofitInterfaceAPI.class);
        Call<RetrofitResponse> call = retrofitInterfaceAPI.getAPIResponse(API_KEY, query_city_name);
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                Log.d("WeatherApp Log", "ListCount: " + response.body().getList().size());
                Log.d("WeatherApp Log", "CityName: " + response.body().getCity().getName());
            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                Log.e("WeatherApp Log", "An error has occured when downloading data. Please delete the internet.");
            }
        });
    }

}
