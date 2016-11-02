package com.example.nvdovin.weatherapp.model;

import com.example.nvdovin.weatherapp.database.model.WeatherData;

public class ForecastNow {

    private Long cityId;
    private String cityName;
    private WeatherData currentCityWeather;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public WeatherData getCurrentCityWeather() {
        return currentCityWeather;
    }

    public void setCurrentCityWeather(WeatherData currentCityWeather) {
        this.currentCityWeather = currentCityWeather;
    }
}
