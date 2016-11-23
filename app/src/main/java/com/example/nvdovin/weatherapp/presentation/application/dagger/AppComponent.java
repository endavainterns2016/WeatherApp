package com.example.nvdovin.weatherapp.presentation.application.dagger;

import android.content.Context;

import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.updater.DataMapper;

import dagger.Component;


@Component(modules = {AppModule.class, DatabaseModule.class})
@AppScope
public interface AppComponent {

    Context context();

    CityService cityService();

    WeatherDataService weatherDataService();

    DataMapper dataMapper();
}
