package com.example.nvdovin.weatherapp.factory;

import android.content.Context;
import android.util.Log;

import com.example.nvdovin.weatherapp.Retrofit.RetrofitInterfaceAPI;
import com.example.nvdovin.weatherapp.model.FirstModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    private Gson gson;
    private Retrofit retrofit;

    public RetrofitFactory() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        /*call.enqueue(new Callback<FirstModel>() {
            @Override
            public void onResponse(Call<FirstModel> call, Response<FirstModel> response) {
                //Log.d("WeatherApp Log", "ListCount: " + response.body().getList().size());
                Log.d("WeatherApp Log", "CityName: " + response.body().getCity().getName());
                GreenDaoFactory greenDaoFactory = new GreenDaoFactory(context);
                greenDaoFactory.insert(response.body());
                Log.d("GREENDAO : " , "SIZE " + greenDaoFactory.loadCities());
            }

            @Override
            public void onFailure(Call<FirstModel> call, Throwable t) {
                Log.e("WeatherApp Log", "An error has occured when downloading data. Please delete the internet.");
            }
        });*/
    }

    public Call<FirstModel> getData(String query_city_name){
        RetrofitInterfaceAPI retrofitInterfaceAPI = retrofit.create(RetrofitInterfaceAPI.class);
        return retrofitInterfaceAPI.getAPIResponse(API_KEY, query_city_name);
    }

}
