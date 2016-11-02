package com.example.nvdovin.weatherapp;

import com.example.nvdovin.weatherapp.model.ForecastNow;

import java.util.List;

interface ForecastView {

    void displayData(List<ForecastNow> data);

    void hideLoading();

}
