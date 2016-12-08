package com.example.nvdovin.weatherapp.presentation.history.grid;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;
import com.example.nvdovin.weatherapp.domain.utils.navigator.OperationNavigation;

import java.util.List;

public class GridHistoryPresenter implements OperationNavigation {


    private Long cityId;
    private Long timestamp;
    private GridHistoryView gridHistoryView;
    private DataMapper dataMapper;
    private Navigator.Builder navBuilder;

    public GridHistoryPresenter(DataMapper dataMapper, GridHistoryView gridHistoryView, Navigator.Builder navBuilder) {
        this.gridHistoryView = gridHistoryView;
        this.dataMapper = dataMapper;
        this.navBuilder = navBuilder;
        gridHistoryView.setViewCallback(this);
    }

    public void getWeatherData() {
        final List<WeatherData> weatherDataList =
                dataMapper.getWeatherDataListByDTs(TimeUtils.getAllPeriodsForDay(timestamp), cityId);
        gridHistoryView.displayHistory(weatherDataList);
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public void navigationButtonHandler() {
        navBuilder.setDestination(DetailActivity.class)
                .setCityId(cityId)
                .setTimestamp(timestamp)
                .commit();
    }
}
