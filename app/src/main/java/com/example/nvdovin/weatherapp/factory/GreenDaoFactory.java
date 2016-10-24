package com.example.nvdovin.weatherapp.factory;

import android.content.Context;

import com.example.nvdovin.weatherapp.greendao.DaoMaster;
import com.example.nvdovin.weatherapp.greendao.DaoSession;
import com.example.nvdovin.weatherapp.model.City;
import com.example.nvdovin.weatherapp.model.Model;
import com.example.nvdovin.weatherapp.model.WeatherList;
import java.util.List;

/**
 * Created by nvdovin on 10/24/2016.
 */

public class GreenDaoFactory {

    private static final String DB_NAME = "WEATHER_DB";


    private final DaoSession daoSession;

    public GreenDaoFactory(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public void insert(Model model){

        final City cityData = model.getCity();

        final List<WeatherList> weatherData = model.getWeatherList();

        for (int i = 0; i < model.getCnt(); i++) {
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
