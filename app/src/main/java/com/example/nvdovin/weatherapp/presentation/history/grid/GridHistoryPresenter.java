package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import java.util.List;

public class GridHistoryPresenter {

    private static final Long DAY_IN_MILLISECONDS_EXCLUSIVE = 75600L;
    public static final int DAYS_AHEAD = 0;
    public static final int DAYS_AGO = 5;
    private final Context context;

    private Long cityId;
    private Long timestamp;
    private GreenDaoFactory greenDaoFactory;
    private GridHistoryView gridHistoryView;
    private TimeUtils timeUtils;

    public GridHistoryPresenter(Context context, GridHistoryView gridHistoryView, Long cityId, Long timestamp) {
        this.cityId = cityId;
        this.timestamp = timestamp;
        this.context = context;
        this.gridHistoryView = gridHistoryView;
        timeUtils = new TimeUtils();
        greenDaoFactory = new GreenDaoFactory(context);

    }

    public void getWeatherData(){
        gridHistoryView.displayHistory();
    }

    public List<WeatherData> getForecast() {
        return greenDaoFactory.getWeatherDataListByDTs(timeUtils.getAllPeriodsForDay(timestamp),cityId);
    }

}
