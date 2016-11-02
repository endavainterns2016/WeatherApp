package com.example.nvdovin.weatherapp.factory;

import android.content.Context;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.database.dao.DaoMaster;
import com.example.nvdovin.weatherapp.database.dao.DaoSession;
import com.example.nvdovin.weatherapp.database.model.City;
import com.example.nvdovin.weatherapp.database.model.WeatherData;
import com.example.nvdovin.weatherapp.model.ForecastNow;
import com.example.nvdovin.weatherapp.utils.Mapper;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoFactory {

    private static final String DB_NAME = "WEATHER_DB";
    private final Context context;

    private final DaoSession daoSession;

    public GreenDaoFactory(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        this.context = context;
        Mapper.setDaoSession(daoSession);
    }

    public void insert(City city) {
        List<WeatherData> weatherDataList = city.getRawWeatherList();
        daoSession.getWeatherDataDao().insertOrReplaceInTx(weatherDataList);
        daoSession.getCityDao().insertOrReplace(city);//TODO implement uniqueId logic
    }

    public List<City> loadCities(){
       return daoSession.getCityDao().loadAll();
    }

    public List<ForecastNow> loadCityWeatherForNow() {

        Long timeNow = System.currentTimeMillis() / 1000;

        List<City> allCities = loadCities();

        List<ForecastNow> forecastNowList = new ArrayList<>();
        for (City city : allCities) {
            ForecastNow forecastNow = new ForecastNow();
            forecastNow.setCityId(city.getId());
            forecastNow.setCityName(city.getName());
            WeatherData weatherDataForNow = daoSession.getWeatherDataDao().queryBuilder().where(
                    new WhereCondition.StringCondition(context.getString(R.string.current_time_query), new String[]{
                            String.valueOf(forecastNow.getCityId()), String.valueOf(timeNow)
                    })).unique();
            forecastNow.setCurrentCityWeather(weatherDataForNow);
            forecastNowList.add(forecastNow);
        }
        return forecastNowList;
    }

}
