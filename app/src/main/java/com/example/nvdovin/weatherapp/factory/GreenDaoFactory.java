package com.example.nvdovin.weatherapp.factory;

import android.content.Context;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.database.SortQueryBuilder;
import com.example.nvdovin.weatherapp.database.dao.DaoMaster;
import com.example.nvdovin.weatherapp.database.dao.DaoSession;
import com.example.nvdovin.weatherapp.database.model.City;
import com.example.nvdovin.weatherapp.database.model.WeatherData;
import com.example.nvdovin.weatherapp.model.CityForecast;
import com.example.nvdovin.weatherapp.utils.Mapper;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        daoSession.getWeatherDataDao().insertOrReplaceInTx(city.getRawWeatherList());
        daoSession.getCityDao().insertOrReplace(city);
    }

    @SafeVarargs
    public final List<CityForecast> loadSortedCities(SortQueryBuilder<Property>... queryBuilders) {
        QueryBuilder<City> qb = daoSession.getCityDao().queryBuilder();
        for (SortQueryBuilder sortQueryBuilder : queryBuilders) {
            if (sortQueryBuilder.isAscending()) {
                qb = qb.orderAsc(sortQueryBuilder.getProperty());
            } else {
                qb = qb.orderDesc(sortQueryBuilder.getProperty());
            }
        }
        return loadCityWeatherForNow(qb.list());
    }

    public List<CityForecast> loadCityWeatherForNow(List<City> cities) {
        Long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        List<CityForecast> cityForecastList = new ArrayList<>();
        for (City city : cities) {
            CityForecast cityForecast = new CityForecast();
            cityForecast.setCityId(city.getId());
            cityForecast.setCityName(city.getName());
            WeatherData weatherDataForNow = daoSession.getWeatherDataDao().queryBuilder().where(
                    new WhereCondition.StringCondition(context.getString(R.string.current_time_query),
                            String.valueOf(cityForecast.getCityId()), String.valueOf(currentTime)
                    )).unique();
            cityForecast.setCurrentCityWeather(weatherDataForNow);
            cityForecastList.add(cityForecast);
        }
        return cityForecastList;
    }

}
