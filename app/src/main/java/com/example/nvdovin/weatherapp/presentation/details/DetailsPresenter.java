package com.example.nvdovin.weatherapp.presentation.details;


import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import java.util.ArrayList;
import java.util.List;

class DetailsPresenter {
    private static final long ONE_DAY_IN_MILLISEC = 86400L;

    private CityService cityService;
    private WeatherDataService weatherDataService;

    DetailsPresenter(CityService cityService, WeatherDataService weatherDataService) {
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
    }

    List<DailyForecast> getDailyForecastList(Long cityId, Long dt, int numberOfDays) {
        List<DailyForecast> dailyForecastList = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {

            dailyForecastList.add(getDailyForecast(cityId, dt + i * ONE_DAY_IN_MILLISEC));
        }
        return dailyForecastList;
    }

    WeatherData getCurrentWeather(Long cityId) {
        return weatherDataService.getUnique(cityId, TimeUtils.getCurrentTime());
    }

    private DailyForecast getDailyForecast(Long cityId, Long dt) {
        DailyForecast dailyForecast = new DailyForecast();
        List<WeatherData> weatherDataForDay = weatherDataService.getWeatherDataForDay(cityId, dt, ONE_DAY_IN_MILLISEC);

        dailyForecast.setWeatherDataList(weatherDataForDay);
        dailyForecast.setCityId(cityId);
        dailyForecast.setDayTempMax(weatherDataService.getTempMax(weatherDataForDay));
        dailyForecast.setDayTempMin(weatherDataService.getTempMin(weatherDataForDay));

        dailyForecast.setDate(TimeUtils.setLongToDate(dt));
        return dailyForecast;
    }

    String getCityName(Long cityId) {
        return cityService.getCityById(cityId).getName();
    }

}
