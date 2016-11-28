package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.view.View;
import android.widget.AdapterView;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;

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
        final List<WeatherData> weatherDataList = weatherDataService.getWeatherDataListByDTs(TimeUtils.getAllPeriodsForDay(timestamp), cityId);
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
