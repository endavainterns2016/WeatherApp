package com.example.nvdovin.weatherapp.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCityListResponse {

    @SerializedName("cities")
    @Expose
    private List<String> cities;
    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
