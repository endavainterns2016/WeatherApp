package com.example.nvdovin.weatherapp.presentation.main.weather.forecast.dagger;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.utils.executor.DefaultThreadPoolExecutor;
import com.example.nvdovin.weatherapp.domain.utils.executor.Executor;
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
    ForecastView provideForecastView() {
        return new ForecastView(forecastFragment);
    }

    @Provides
    @ForecastFragmentScope
    ForecastPresenter provideForecastPresenter(Executor executor,
                                               CityService cityService,
                                               ForecastView view,
                                               SharedPrefs sharedPrefs,
                                               DataMapper dataMapper,
                                               DefaultThreadPoolExecutor defaultThreadPoolExecutor,
                                               SortQueryBuilder sortQueryBuilder) {
        return new ForecastPresenter(executor, cityService, view, sharedPrefs, dataMapper, defaultThreadPoolExecutor, sortQueryBuilder);
    }
}
