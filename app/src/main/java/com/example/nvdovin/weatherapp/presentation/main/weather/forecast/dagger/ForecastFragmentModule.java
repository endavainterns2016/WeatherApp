package com.example.nvdovin.weatherapp.presentation.main.weather.forecast.dagger;

import android.content.Context;

import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
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
    ForecastPresenter provideForecastPresenter(CityService cityService, WeatherDataService weatherDataService, ForecastView view, Context context, DataMapper dataMapper){
        return new ForecastPresenter(cityService, weatherDataService, view, context, dataMapper);
    }
}
