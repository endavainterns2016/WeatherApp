package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;

import java.util.List;

public class GridHistoryPresenter {


    private Long cityId;
    private Long timestamp;
    private GridHistoryView gridHistoryView;
    private DataMapper dataMapper;

    public GridHistoryPresenter(DataMapper dataMapper, GridHistoryView gridHistoryView) {
        this.gridHistoryView = gridHistoryView;
        this.dataMapper = dataMapper;

    }

    public void getWeatherData() {
        final List<WeatherData> weatherDataList = dataMapper.getWeatherDataListByDTs(TimeUtils.getAllPeriodsForDay(timestamp), cityId);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailActivity.start(gridHistoryView.getView().getContext(), weatherDataList.get(position));
            }
        };
        gridHistoryView.displayHistory(weatherDataList, onItemClickListener);
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
