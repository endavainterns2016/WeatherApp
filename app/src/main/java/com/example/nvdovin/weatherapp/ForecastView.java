package com.example.nvdovin.weatherapp;

import com.example.nvdovin.weatherapp.model.CityForecast;

import java.util.List;

interface ForecastView {

    void displayData(List<CityForecast> data);

    void hideLoading();

    void setRefreshing(boolean refreshing);
}
