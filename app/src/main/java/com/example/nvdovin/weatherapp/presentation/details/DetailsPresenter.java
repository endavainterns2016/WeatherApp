package com.example.nvdovin.weatherapp.presentation.details;


import android.content.Context;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import java.util.ArrayList;
import java.util.List;

class DetailsPresenter {
    private static final long ONE_DAY_IN_MILLISEC = 86400L;
    private GreenDaoFactory greenDaoFactory;

    DetailsPresenter(Context context) {
        greenDaoFactory = new GreenDaoFactory(context);
    }

    List<DailyForecast> getDailyForecastList(Long cityId, Long dt, int numberOfDays) {
        List<DailyForecast> dailyForecastList = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {

            dailyForecastList.add(getDailyForecast(cityId, dt + i * ONE_DAY_IN_MILLISEC));
        }
        return dailyForecastList;
    }

    WeatherData getCurrentWeather(Long cityId) {
        return greenDaoFactory.getUnique(cityId, TimeUtils.getCurrentTime());
    }

    private DailyForecast getDailyForecast(Long cityId, Long dt) {
        DailyForecast dailyForecast = new DailyForecast();
        List<WeatherData> weatherDataForDay = greenDaoFactory.getWeatherDataForDay(cityId, dt, ONE_DAY_IN_MILLISEC);

        dailyForecast.setWeatherDataList(weatherDataForDay);
        dailyForecast.setCityId(cityId);
        dailyForecast.setDayTempMax(greenDaoFactory.getTempMax(weatherDataForDay));
        dailyForecast.setDayTempMin(greenDaoFactory.getTempMin(weatherDataForDay));

        dailyForecast.setDate(TimeUtils.setLongToDate(dt));
        return dailyForecast;
    }

    String getCityName(Long cityId) {
        return greenDaoFactory.getCityById(cityId).getName();
    }

}
