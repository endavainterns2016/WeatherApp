package com.example.nvdovin.weatherapp.domain.model;


import com.example.nvdovin.weatherapp.data.model.WeatherData;

import java.util.Date;
import java.util.List;

public class DailyForecast {
    private Long cityId;
    private int dayTempMax;
    private int dayTempMin;
    private Date date;
    List<WeatherData> weatherDataList;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public int getDayTempMax() {
        return dayTempMax;
    }

    public void setDayTempMax(int dayTempMax) {
        this.dayTempMax = dayTempMax;
    }

    public int getDayTempMin() {
        return dayTempMin;
    }

    public void setDayTempMin(int dayTempMin) {
        this.dayTempMin = dayTempMin;
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
