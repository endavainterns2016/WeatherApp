package com.example.nvdovin.weatherapp.presentation.application.dagger;


import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.network.api.WeatherApi;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.executor.Executor;
import com.example.nvdovin.weatherapp.domain.utils.updater.DataMapper;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DatabaseModule.class, NetworkModule.class})
public class ExecutorModule {

    @Provides
    @AppScope
    Executor provideExecutor(CityService cityService,
                             WeatherDataService weatherDataService,
                             DataMapper dataMapper,
                             SortQueryBuilder sortQueryBuilder,
                             WeatherApi weatherApi) {
        return new Executor(cityService, weatherDataService, dataMapper, sortQueryBuilder, weatherApi);
    }
}
