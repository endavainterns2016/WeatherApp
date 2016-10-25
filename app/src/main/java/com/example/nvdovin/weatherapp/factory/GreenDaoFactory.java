package com.example.nvdovin.weatherapp.factory;

import android.content.Context;

import com.example.nvdovin.weatherapp.greendao.DaoMaster;
import com.example.nvdovin.weatherapp.greendao.DaoSession;
import com.example.nvdovin.weatherapp.model.City;
import com.example.nvdovin.weatherapp.model.Forecast;
import com.example.nvdovin.weatherapp.model.WeatherList;
import java.util.List;

public class GreenDaoFactory {

    private static final String DB_NAME = "WEATHER_DB";


    private final DaoSession daoSession;

    public GreenDaoFactory(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public void insert(Forecast forecast){

        final City cityData = forecast.getCity();

        final List<WeatherList> weatherData = forecast.getWeatherList();

        for (int i = 0; i < forecast.getCnt(); i++) {
            WeatherList dataitem = weatherData.get(i);
            daoSession.getCloudsDao().insert(dataitem.getRawClouds());
            daoSession.getMainDao().insert(dataitem.getRawMain());
            daoSession.getSys_Dao().insert(dataitem.getRawSys());
            daoSession.getWeatherDao().insertInTx(dataitem.getRawWeather());
            daoSession.getWindDao().insert(dataitem.getRawWind());
            daoSession.getWeatherListDao().insert(dataitem);
        }
        cityData.setRawWeatherList(weatherData);
        daoSession.getCoordDao().insert(cityData.getRawCoord());
        daoSession.getSysDao().insert(cityData.getRawSys());
        daoSession.getCityDao().insert(cityData);

    }

    public List<City> loadCities(){
       return daoSession.getCityDao().loadAll();
    }

}
