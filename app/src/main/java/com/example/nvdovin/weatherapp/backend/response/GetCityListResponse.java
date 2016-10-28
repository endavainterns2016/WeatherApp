package com.example.nvdovin.weatherapp.backend.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCityListResponse {

    @SerializedName("cities")
    List<String> cities;

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
