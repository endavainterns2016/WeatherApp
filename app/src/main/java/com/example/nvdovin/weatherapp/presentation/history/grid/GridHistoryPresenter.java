package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import java.util.List;

public class GridHistoryPresenter {

    private Long cityId;
    private Long timestamp;
    private GridHistoryView gridHistoryView;
    private CityService cityService;
    private WeatherDataService weatherDataService;

    public GridHistoryPresenter(CityService cityService, WeatherDataService weatherDataService, Context context, GridHistoryView gridHistoryView, Long cityId, Long timestamp) {
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
        this.cityId = cityId;
        this.timestamp = timestamp;
        this.gridHistoryView = gridHistoryView;

    }

    public void getWeatherData() {
        gridHistoryView.displayHistory();
    }

    public List<WeatherData> getForecast() {
        return weatherDataService.getWeatherDataListByDTs(TimeUtils.getAllPeriodsForDay(timestamp), cityId);
    }

}
