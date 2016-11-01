package com.example.nvdovin.weatherapp.presentation.main.weather.detail;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.factory.GreenDaoFactory;

import java.util.List;

public class DetailsPresenter {

    private Long cityId;
    private Long timestamp;
    private GreenDaoFactory greenDaoFactory;

    public DetailsPresenter(Long cityId, Long timestamp, Context context) {
        this.cityId = cityId;
        this.timestamp = timestamp;
        greenDaoFactory = new GreenDaoFactory(context);
    }

    public List<WeatherData> getForecast(){
        return greenDaoFactory.getWeatherDataForDay(cityId, timestamp);
    }

    public WeatherData getIdByDt(Long cityId, Long timestamp) {
        return greenDaoFactory.getWeatherDataByDT(timestamp, cityId);
    }

    public String getCityName(Long cityId){
        return greenDaoFactory.getCityById(cityId);
    }
}
