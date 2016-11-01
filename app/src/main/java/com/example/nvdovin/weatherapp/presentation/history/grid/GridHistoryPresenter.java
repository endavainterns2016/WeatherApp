package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import java.util.List;

public class GridHistoryPresenter {

    private Long cityId;
    private Long timestamp;
    private GreenDaoFactory greenDaoFactory;
    private GridHistoryView gridHistoryView;

    public GridHistoryPresenter(Context context, GridHistoryView gridHistoryView, Long cityId, Long timestamp) {
        this.cityId = cityId;
        this.timestamp = timestamp;
        this.gridHistoryView = gridHistoryView;
        greenDaoFactory = new GreenDaoFactory(context);

    }

    public void getWeatherData() {
        gridHistoryView.displayHistory();
    }

    public List<WeatherData> getForecast() {
        return greenDaoFactory.getWeatherDataListByDTs(TimeUtils.getAllPeriodsForDay(timestamp), cityId);
    }

}
