package com.example.nvdovin.weatherapp.presentation.application;

import android.app.Application;
import android.content.Context;

import com.example.nvdovin.weatherapp.presentation.application.dagger.AppComponent;
import com.example.nvdovin.weatherapp.presentation.application.dagger.AppModule;
import com.example.nvdovin.weatherapp.presentation.application.dagger.DaggerAppComponent;

public class WeatherApplication extends Application {

    private static AppComponent appComponent;

    public static WeatherApplication get(Context context) {
        return (WeatherApplication) context.getApplicationContext();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent();

    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

}
