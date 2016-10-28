package com.example.nvdovin.weatherapp.utils;

import com.example.nvdovin.weatherapp.database.dao.DaoSession;
import com.example.nvdovin.weatherapp.database.dao.WeatherDataDao;
import com.example.nvdovin.weatherapp.database.model.WeatherData;

import java.util.List;

public class Mapper {

    private static DaoSession session;

    public static void setDaoSession(DaoSession daoSession) {
        session = daoSession;
    }

    public static List<WeatherData> updatedWeatherData(List<WeatherData> weatherDataListFromNetwork) {

        for (WeatherData weatherDataFromNetwork : weatherDataListFromNetwork) {
            WeatherData weatherFromDB = getWeatherByUniqueId(weatherDataFromNetwork);
            if (weatherFromDB != null) {
                weatherDataFromNetwork.setId(weatherFromDB.getId());
            }
        }

        return weatherDataListFromNetwork;

    }

    private static WeatherData getWeatherByUniqueId(WeatherData weatherDataFromNetwork) {
        return session
                .getWeatherDataDao()
                .queryBuilder()
                .where(WeatherDataDao.Properties.UniqueId.eq(weatherDataFromNetwork.getUniqueId())).unique();
    }


}
