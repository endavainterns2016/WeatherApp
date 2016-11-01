package com.example.nvdovin.weatherapp.domain.utils.updater;

import com.example.nvdovin.weatherapp.data.dao.DaoSession;
import com.example.nvdovin.weatherapp.data.dao.WeatherDataDao;
import com.example.nvdovin.weatherapp.data.model.WeatherData;

import java.util.List;

public class WeatherDataUpdater {

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
