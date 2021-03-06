package com.example.nvdovin.weatherapp.domain.service;


import android.content.Context;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.dao.DaoSession;
import com.example.nvdovin.weatherapp.data.dao.WeatherDataDao;
import com.example.nvdovin.weatherapp.data.model.WeatherData;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public class WeatherDataService {

    private DaoSession daoSession;
    private Context context;

    public WeatherDataService(DaoSession daoSession, Context context) {
        this.daoSession = daoSession;
        this.context = context;
    }

    public void insert(List<WeatherData> weatherData) {
        daoSession.getWeatherDataDao().insertOrReplaceInTx(weatherData);
    }

    public List<WeatherData> getWeatherDataForDay(Long cityId, Long dt, Long period) {
        return daoSession.getWeatherDataDao().queryBuilder()
                .where(WeatherDataDao.Properties.CityId.eq(cityId), WeatherDataDao.Properties.Dt.ge(dt),
                        WeatherDataDao.Properties.Dt.le(dt + period))
                .list();

    }

    public WeatherData getWeatherDataByDT(Long dt, Long cityId) {
        return daoSession
                .getWeatherDataDao()
                .queryBuilder()
                .where(
                        WeatherDataDao.Properties.CityId.eq(cityId),
                        WeatherDataDao.Properties.Dt.eq(dt))
                .unique();
    }


    public WeatherData getWeatherByUniqueId(WeatherData weatherDataFromNetwork) {
        return daoSession
                .getWeatherDataDao()
                .queryBuilder()
                .where(WeatherDataDao.Properties.UniqueId.eq(weatherDataFromNetwork.getUniqueId()))
                .unique();
    }

    public WeatherData getUnique(Long cityId, long time) {
        return daoSession
                .getWeatherDataDao()
                .queryBuilder()
                .where(
                        new WhereCondition.StringCondition(context.getString(R.string.time_query),
                                String.valueOf(cityId), String.valueOf(time)
                        ))
                .orderAsc(WeatherDataDao.Properties.Dt)
                .limit(1)
                .unique();
    }


}
