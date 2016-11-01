package com.example.nvdovin.weatherapp.presentation.history.model;

import java.util.List;

public class CityDate {

    private Long cityId;
    private List<Long> timestampList;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public List<Long> getTimestampList() {
        return timestampList;
    }

    public void setTimestampList(List<Long> timestampList) {
        this.timestampList = timestampList;
    }
}
