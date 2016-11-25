package com.example.nvdovin.weatherapp.presentation.history.grid;

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

    public GridHistoryPresenter(CityService cityService, WeatherDataService weatherDataService, GridHistoryView gridHistoryView) {
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
        this.gridHistoryView = gridHistoryView;

    }

    public void getWeatherData() {
        List<WeatherData> weatherDataList = weatherDataService.getWeatherDataListByDTs(TimeUtils.getAllPeriodsForDay(timestamp), cityId);
        gridHistoryView.displayHistory(weatherDataList);
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
