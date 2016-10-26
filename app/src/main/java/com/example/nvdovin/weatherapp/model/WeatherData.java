package com.example.nvdovin.weatherapp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class WeatherData {

    @Id(autoincrement = true)
    private Long id;

    @Index(unique = true)
    private String uniqueId;

    private Long dt;
    private Double temp;
    private Double tempMin;
    private Double tempMax;
    private Double pressure;
    private Double humidity;
    private String weather;
    private String weatherDescription;
    private String weatherIcon;
    private Double clouds;
    private Double windSpeed;
    private Double rain;

    @Generated(hash = 295830555)
    public WeatherData(Long id, String uniqueId, Long dt, Double temp,
                       Double tempMin, Double tempMax, Double pressure, Double humidity,
                       String weather, String weatherDescription, String weatherIcon,
                       Double clouds, Double windSpeed, Double rain) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.dt = dt;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.rain = rain;
    }

    @Generated(hash = 1331017575)
    public WeatherData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Long getDt() {
        return this.dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Double getTemp() {
        return this.temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTempMin() {
        return this.tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return this.tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getPressure() {
        return this.pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return this.humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getWeather() {
        return this.weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeatherDescription() {
        return this.weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return this.weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public Double getClouds() {
        return this.clouds;
    }

    public void setClouds(Double clouds) {
        this.clouds = clouds;
    }

    public Double getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getRain() {
        return this.rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

}
