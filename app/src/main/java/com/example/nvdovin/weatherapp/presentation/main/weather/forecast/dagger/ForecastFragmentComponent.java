package com.example.nvdovin.weatherapp.presentation.main.weather.forecast.dagger;

import com.example.nvdovin.weatherapp.presentation.application.dagger.AppComponent;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.ForecastFragment;

import dagger.Component;

@Component(modules = ForecastFragmentModule.class, dependencies = AppComponent.class)
@ForecastFragmentScope
public interface ForecastFragmentComponent {

    void inject(ForecastFragment forecastFragment);

}
