package com.example.nvdovin.weatherapp.presentation.application.dagger;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.dao.DaoMaster;
import com.example.nvdovin.weatherapp.data.dao.DaoSession;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.updater.DataMapper;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private static final String DB_NAME = "WEATHER_DB";

    @Provides
    @AppScope
    public DaoSession provideDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        return daoMaster.newSession();
    }

    @Provides
    @AppScope
    public CityService provideCityService(DaoSession daoSession, Context context) {
        return new CityService(daoSession, context);
    }

    @Provides
    @AppScope
    public WeatherDataService provideWeatherDataService(DaoSession daoSession, Context context) {
        return new WeatherDataService(daoSession, context);
    }

    @Provides
    @AppScope
    public DataMapper provideDataMapper(WeatherDataService weatherDataService, CityService cityService) {
        return new DataMapper(weatherDataService, cityService);
    }
}
