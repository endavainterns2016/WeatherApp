package com.example.nvdovin.weatherapp.domain.utils.updater;

import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataMapper {

    private WeatherDataService weatherDataService;
    private CityService cityService;

    public DataMapper(WeatherDataService weatherDataService, CityService cityService) {
        this.weatherDataService = weatherDataService;
        this.cityService = cityService;
    }

    public List<WeatherData> updatedWeatherData(List<WeatherData> weatherDataListFromNetwork) {

        for (WeatherData weatherDataFromNetwork : weatherDataListFromNetwork) {
            WeatherData weatherFromDB = weatherDataService.getWeatherByUniqueId(weatherDataFromNetwork);
            if (weatherFromDB != null) {
                weatherDataFromNetwork.setId(weatherFromDB.getId());
            }
        }

        return weatherDataListFromNetwork;
    }

    public List<CityForecast> loadCityWeatherForNow(List<City> cities) {
        Long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        List<CityForecast> cityForecastList = new ArrayList<>();
        for (City city : cities) {
            CityForecast cityForecast = new CityForecast();
            cityForecast.setCityId(city.getId());
            cityForecast.setCityName(city.getName());
            WeatherData weatherDataForNow = weatherDataService.getUnique(cityForecast.getCityId(), currentTime);
            cityForecast.setCurrentCityWeather(weatherDataForNow);
            cityForecastList.add(cityForecast);
        }

        return cityForecastList;
    }

}
