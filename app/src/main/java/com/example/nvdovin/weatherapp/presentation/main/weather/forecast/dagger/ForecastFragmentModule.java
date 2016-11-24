package com.example.nvdovin.weatherapp.presentation.main.weather.forecast.dagger;

import android.content.Context;

import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.executor.Executor;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.updater.DataMapper;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.ForecastFragment;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.ForecastPresenter;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.ForecastView;

import dagger.Module;
import dagger.Provides;

@Module
public class ForecastFragmentModule {

    private ForecastFragment forecastFragment;

    public ForecastFragmentModule(ForecastFragment forecastFragment) {
        this.forecastFragment = forecastFragment;
    }

    @Provides
    @ForecastFragmentScope
    ForecastFragment provideForecastFragment(){
        return forecastFragment;
    }

    @Provides
    @ForecastFragmentScope
    ForecastView provideForecastView(){
        return new ForecastView(forecastFragment);
    }

    @Provides
    @ForecastFragmentScope
    ForecastPresenter provideForecastPresenter(Executor executor, CityService cityService, ForecastView view, SharedPrefs sharedPrefs, DataMapper dataMapper){
        return new ForecastPresenter(executor, cityService, view, sharedPrefs, dataMapper);
    }
}
