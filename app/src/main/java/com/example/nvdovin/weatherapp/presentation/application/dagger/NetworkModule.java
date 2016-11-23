package com.example.nvdovin.weatherapp.presentation.application.dagger;

import com.example.nvdovin.weatherapp.data.network.api.WeatherApi;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = DeserializerModule.class)
public class NetworkModule {
    private static final String BASE_URL = "http://api.openweathermap.org";


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
    }

    @Provides
    @Singleton
    WeatherApi provideWeatherApi(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(WeatherApi.class);
    }

}
