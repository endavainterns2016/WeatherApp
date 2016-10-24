package com.example.nvdovin.weatherapp;

import com.example.nvdovin.weatherapp.model.City;

import java.util.List;

public interface MainView {

    void displayData(List<City> data);
    void showLoading();
    void hideLoading();

}
