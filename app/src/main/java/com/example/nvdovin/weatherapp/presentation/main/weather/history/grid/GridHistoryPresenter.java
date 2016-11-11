package com.example.nvdovin.weatherapp.presentation.main.weather.history.grid;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.factory.GreenDaoFactory;

import java.util.List;

public class GridHistoryPresenter {

    private static final Long DAY_IN_MILLISECONDS_EXCLUSIVE = 75600L;
    private final Context context;

    private Long cityId;
    private Long timestamp;
    private GreenDaoFactory greenDaoFactory;
    private GridHistoryView gridHistoryView;

    public GridHistoryPresenter(Context context, GridHistoryView gridHistoryView, Long cityId, Long timestamp) {
        this.cityId = cityId;
        this.timestamp = timestamp;
        this.context = context;
        this.gridHistoryView = gridHistoryView;
        greenDaoFactory = new GreenDaoFactory(context);

    }

    public void getWeatherData(){
        gridHistoryView.displayHistory();
    }

    public List<WeatherData> getForecast() {
        return greenDaoFactory.getWeatherDataForDay(cityId, timestamp, DAY_IN_MILLISECONDS_EXCLUSIVE);
    }

}
