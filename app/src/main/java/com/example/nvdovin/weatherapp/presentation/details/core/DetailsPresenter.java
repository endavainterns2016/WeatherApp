package com.example.nvdovin.weatherapp.presentation.details.core;


import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import java.util.List;

public class DetailsPresenter {

    private static final int NUMBER_OF_DAYS_TO_FORECAST = 4;

    private CityService cityService;
    private WeatherDataService weatherDataService;
    private DataMapper dataMapper;
    private DetailView detailView;

    public DetailsPresenter(CityService cityService,
                            WeatherDataService weatherDataService,
                            DataMapper dataMapper,
                            DetailView detailView) {
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
        this.dataMapper = dataMapper;
        this.detailView = detailView;
    }

    public void setupDetailView(Long cityId, Long timestamp){
        String cityName = cityService.getCityById(cityId).getName();
        List<DailyForecast> dailyForecastList = dataMapper.getDailyForecastList(cityId, timestamp, NUMBER_OF_DAYS_TO_FORECAST);
        WeatherData weatherData = weatherDataService.getUnique(cityId, TimeUtils.getCurrentTime());
        detailView.setupView(cityName, dailyForecastList, weatherData);
    }

}
