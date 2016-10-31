package com.example.nvdovin.weatherapp;

import com.example.nvdovin.weatherapp.database.model.City;

import java.util.List;

interface ForecastView {

    void displayData(List<City> data);

    void hideLoading();

}
