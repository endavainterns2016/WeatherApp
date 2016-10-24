package com.example.nvdovin.weatherapp.factory;

import android.content.Context;

import com.example.nvdovin.weatherapp.greendao.DaoMaster;
import com.example.nvdovin.weatherapp.greendao.DaoSession;
import com.example.nvdovin.weatherapp.model.City;
import com.example.nvdovin.weatherapp.model.Clouds;
import com.example.nvdovin.weatherapp.model.Coord;
import com.example.nvdovin.weatherapp.model.FirstModel;
import com.example.nvdovin.weatherapp.model.Main;
import com.example.nvdovin.weatherapp.model.Rain;
import com.example.nvdovin.weatherapp.model.Sys;
import com.example.nvdovin.weatherapp.model.Sys_;
import com.example.nvdovin.weatherapp.model.Weather;
import com.example.nvdovin.weatherapp.model.WeatherList;
import com.example.nvdovin.weatherapp.model.Wind;

import java.util.ArrayList;
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

    public void insert(FirstModel firstModel){

        final City cityData = firstModel.getCity();

        final List<WeatherList> weatherData = firstModel.getWeatherList();

        for (int i = 0; i < firstModel.getCnt(); i++) {
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
        daoSession.getCityDao().insertInTx(cityData);

    }

    public List<City> loadCities(){
       return daoSession.getCityDao().loadAll();
    }

}
