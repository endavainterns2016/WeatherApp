package com.example.nvdovin.weatherapp.factory;

import android.content.Context;

import com.example.nvdovin.weatherapp.database.SortQueryBuilder;
import com.example.nvdovin.weatherapp.database.dao.DaoMaster;
import com.example.nvdovin.weatherapp.database.dao.DaoSession;
import com.example.nvdovin.weatherapp.database.model.City;
import com.example.nvdovin.weatherapp.database.model.WeatherData;
import com.example.nvdovin.weatherapp.utils.Mapper;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class GreenDaoFactory {

    private static final String DB_NAME = "WEATHER_DB";


    private final DaoSession daoSession;

    public GreenDaoFactory(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        Mapper.setDaoSession(daoSession);
    }

    public void insert(City city) {
        List<WeatherData> weatherDataList = city.getRawWeatherList();
        daoSession.getWeatherDataDao().insertOrReplaceInTx(weatherDataList);
        daoSession.getCityDao().insertOrReplace(city);//TODO implement uniqueId logic
    }

    public List<City> loadSortedCities(SortQueryBuilder<Property>... queryBuilders) {

        if (null != queryBuilders) {
            QueryBuilder qb = daoSession.getCityDao().queryBuilder();
            for (SortQueryBuilder sortQueryBuilder : queryBuilders) {
                if (sortQueryBuilder.isAscending()) {
                    return qb.orderAsc(sortQueryBuilder.getProperty()).list();
                }
                return qb.orderDesc(sortQueryBuilder.getProperty()).list();

            }
        }
        return daoSession.getCityDao().loadAll();
    }

}
