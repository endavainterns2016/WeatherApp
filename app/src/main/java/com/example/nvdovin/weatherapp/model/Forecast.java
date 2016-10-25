
package com.example.nvdovin.weatherapp.model;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    private static final String CITY = "city";
    private static final String COD = "cod";
    private static final String MESSAGE = "message";
    private static final String COUNT = "cnt";
    private static final String LIST = "list";
    @SerializedName(CITY)
    @Expose
    private City city;
    @SerializedName(COD)
    @Expose
    private String cod;
    @SerializedName(MESSAGE)
    @Expose
    private Double message;
    @SerializedName(COUNT)
    @Expose
    private Integer cnt;
    @SerializedName(LIST)
    @Expose
    private java.util.List<WeatherList> weatherList = new ArrayList<WeatherList>();

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<WeatherList> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(java.util.List<WeatherList> weatherList) {
        this.weatherList = weatherList;
    }

}
