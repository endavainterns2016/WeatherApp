package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.view.View;
import android.widget.AdapterView;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;

import java.util.List;

public class GridHistoryPresenter {


    private Long cityId;
    private Long timestamp;
    private GridHistoryView gridHistoryView;
    private DataMapper dataMapper;
    private Navigator.Builder navBuilder;

    public GridHistoryPresenter(DataMapper dataMapper, GridHistoryView gridHistoryView, Navigator.Builder navBuilder) {
        this.gridHistoryView = gridHistoryView;
        this.dataMapper = dataMapper;
        this.navBuilder = navBuilder;

    }

    public void getWeatherData() {
        final List<WeatherData> weatherDataList = dataMapper.getWeatherDataListByDTs(TimeUtils.getAllPeriodsForDay(timestamp), cityId);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navBuilder.setDestination(DetailActivity.class)
                        .setCityId(cityId)
                        .setTimestamp(timestamp)
                        .commit();
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
