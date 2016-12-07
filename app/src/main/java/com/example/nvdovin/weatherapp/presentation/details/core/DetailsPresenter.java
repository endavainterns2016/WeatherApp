package com.example.nvdovin.weatherapp.presentation.details.core;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.presentation.history.HistoryActivity;

import java.util.List;

public class DetailsPresenter implements ViewCallback{

    private static final int NUMBER_OF_DAYS_TO_FORECAST = 4;
    private static final String ARGS_KEY = "HISTORY_ARGS";
    private static final String CITY_ID_KEY = "CITY_ID_KEY";

    private CityService cityService;
    private WeatherDataService weatherDataService;
    private DataMapper dataMapper;
    private DetailView detailView;
    private WeatherData weatherData;
    private Context context;
    private SharedPrefs sharedPrefs;

    public DetailsPresenter(CityService cityService,
                            WeatherDataService weatherDataService,
                            DataMapper dataMapper,
                            DetailView detailView,
                            Context context,
                            SharedPrefs sharedPrefs) {
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
        this.dataMapper = dataMapper;
        this.detailView = detailView;
        this.context = context;
        this.sharedPrefs = sharedPrefs;
    }

    public void setupDetailView(Long cityId, Long timestamp){
        String cityName = cityService.getCityById(cityId).getName();
        List<DailyForecast> dailyForecastList = dataMapper.getDailyForecastList(cityId, timestamp, NUMBER_OF_DAYS_TO_FORECAST);
        weatherData = weatherDataService.getUnique(cityId, TimeUtils.getCurrentTime());
        detailView.setupView(cityName, dailyForecastList, weatherData, sharedPrefs);
    }

    @Override
    public void historyClickHandler() {
        Intent historyIntent = new Intent(context, HistoryActivity.class);
        Bundle sentBundle = new Bundle();
        sentBundle.putLong(CITY_ID_KEY, weatherData.getCityId());
        historyIntent.putExtra(ARGS_KEY, sentBundle);
        historyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(historyIntent);
    }

    public ViewCallback getCallBack(){
        return this;
    }
}
