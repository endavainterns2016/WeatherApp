package com.example.nvdovin.weatherapp.factory;

import com.example.nvdovin.weatherapp.Retrofit.ApiRequest;
import com.example.nvdovin.weatherapp.model.FirstModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    }

    public FirstModel getData(String query_city_name) throws IOException {
        ApiRequest apiRequest = retrofit.create(ApiRequest.class);
        return apiRequest.getResponse(API_KEY, query_city_name).execute().body();
    }

}
