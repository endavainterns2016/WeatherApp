package com.example.nvdovin.weatherapp.presentation.main.weather.forecast.dagger;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.network.api.WeatherApi;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.rx.RxSchedulers;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
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
    ForecastFragment provideForecastFragment() {
        return forecastFragment;
    }

    @Provides
    @ForecastFragmentScope
    ForecastView provideForecastView(SharedPrefs sharedPrefs, ImageUtils imageUtils) {
        return new ForecastView(forecastFragment, sharedPrefs, imageUtils);
    }

    @Provides
    @ForecastFragmentScope
    ForecastPresenter provideForecastPresenter(CityService cityService,
                                               WeatherDataService weatherDataService,
                                               ForecastView view,
                                               SharedPrefs sharedPrefs,
                                               DataMapper dataMapper,
                                               SortQueryBuilder sortQueryBuilder,
                                               Navigator.Builder builder,
                                               WeatherApi weatherApi,
                                               RxSchedulers rxSchedulers) {
        return new ForecastPresenter(cityService,
                weatherDataService,
                view,
                sharedPrefs,
                dataMapper,
                sortQueryBuilder,
                builder,
                weatherApi,
                rxSchedulers);
    }
}
