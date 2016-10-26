package com.example.nvdovin.weatherapp.factory;

import com.example.nvdovin.weatherapp.model.City;
import com.example.nvdovin.weatherapp.retrofit.CityDeserializer;
import com.example.nvdovin.weatherapp.retrofit.WeatherApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static final String BASE_URL = "http://api.openweathermap.org";
    private static final String API_KEY = "94e17e8755c5bc98caaf0a25e9c15d3f";
    private Retrofit retrofit;

    public RetrofitFactory() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(City.class, new CityDeserializer())
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private OkHttpClient createClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public City getData(String cityName) throws IOException {
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        return weatherApi.getWeatherData(API_KEY, cityName).execute().body();
    }

}
