package com.example.nvdovin.weatherapp.presentation.details.core;


import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.navigator.OperationNavigation;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.presentation.history.HistoryActivity;

import java.util.List;

import static com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils.MILLISECONDS;

public class DetailsPresenter implements OperationNavigation {

    private static final int NUMBER_OF_DAYS_TO_FORECAST = 4;

    private CityService cityService;
    private WeatherDataService weatherDataService;
    private DataMapper dataMapper;
    private DetailView detailView;
    private WeatherData weatherData;
    private Navigator.Builder navBuilder;
    private SharedPrefs sharedPrefs;

    public DetailsPresenter(CityService cityService,
                            WeatherDataService weatherDataService,
                            DataMapper dataMapper,
                            DetailView detailView,
                            Navigator.Builder navBuilder,
                            SharedPrefs sharedPrefs) {
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
        this.dataMapper = dataMapper;
        this.detailView = detailView;
        this.sharedPrefs = sharedPrefs;
        this.navBuilder = navBuilder;
    }

    public void setupDetailView(Long cityId, Long timestamp) {
        String cityName = cityService.getCityById(cityId).getName();
        List<DailyForecast> dailyForecastList = dataMapper.getDailyForecastList(cityId, timestamp, NUMBER_OF_DAYS_TO_FORECAST);
        weatherData = weatherDataService.getUnique(cityId, System.currentTimeMillis() / MILLISECONDS);
        detailView.setupView(cityName, dailyForecastList, weatherData, sharedPrefs);
    }

    @Override
    public void navigationButtonHandler() {

        navBuilder.setDestination(HistoryActivity.class)
                .setCityId(weatherData.getCityId())
                .commit();

    }
    public OperationNavigation getCallBack() {
        return this;
    }
}
