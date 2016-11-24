package com.example.nvdovin.weatherapp.presentation.application.dagger;


import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.dao.CityDao;
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
    Executor provideExecutor(WeatherApi weatherApi,
                             CityService cityService,
                             WeatherDataService weatherDataService,
                             DataMapper dataMapper,
                             SortQueryBuilder sortQueryBuilder) {
        return new Executor(weatherApi, sortQueryBuilder, cityService, weatherDataService, dataMapper );
    }


    //With use of Qualifiers, different properties can be set
    //For now, only sortByName will be used

    @Provides
    @AppScope
    SortQueryBuilder provideSortQueryBuilder(){
        SortQueryBuilder sortByName = new SortQueryBuilder();
        sortByName.setAscending(true);
        sortByName.setProperty(CityDao.Properties.Name);
        return sortByName;
    }

}
