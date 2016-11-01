package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import com.example.nvdovin.weatherapp.domain.model.CityForecast;

import java.util.List;

public interface ForecastView {

    void displayData(List<CityForecast> data);

    void hideLoading();

    void setRefreshing(boolean refreshing);


}
