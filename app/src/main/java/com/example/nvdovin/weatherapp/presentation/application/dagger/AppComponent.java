package com.example.nvdovin.weatherapp.presentation.application.dagger;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.network.api.WeatherApi;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.rx.RxSchedulers;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;

import dagger.Component;


@Component(modules = {AppModule.class,
        DatabaseModule.class,
        NetworkModule.class,
        SharedPrefsModule.class,
        DesignModule.class})
@AppScope
public interface AppComponent {

    WeatherApi weatherApi();

    Context context();

    Navigator.Builder navBuilder();

    CityService cityService();

    WeatherDataService weatherDataService();

    DataMapper dataMapper();

    SharedPrefs sharedPrefs();

    SortQueryBuilder sortQueryBuilder();

    ImageUtils imageUtils();

    RxSchedulers schedulers();

}
