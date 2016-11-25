package com.example.nvdovin.weatherapp.domain.utils.executor;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.data.network.api.WeatherApi;
import com.example.nvdovin.weatherapp.data.network.response.GetCityListResponse;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class Executor implements Operation {

    private static final String APIARY_MOCK_CITIES_URL = "https://private-f5a5b-citiesapi.apiary-mock.com/cities";
    private static final String API_KEY = "94e17e8755c5bc98caaf0a25e9c15d3f";
    private CityService cityService;
    private WeatherDataService weatherDataService;
    private DataMapper dataMapper;
    private SortQueryBuilder sortQueryBuilder;
    private WeatherApi weatherApi;

    public Executor(WeatherApi weatherApi,
                    SortQueryBuilder sortQueryBuilder,
                    CityService cityService,
                    WeatherDataService weatherDataService,
                    DataMapper dataMapper) {

        this.sortQueryBuilder = sortQueryBuilder;
        this.weatherApi = weatherApi;
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
        this.dataMapper = dataMapper;
    }

    @Override
    public void execute() throws Exception {

        final GetCityListResponse cityResponse = weatherApi.getCities(APIARY_MOCK_CITIES_URL).execute().body();

        final List<String> cityList = cityResponse.getCities();
        for (String cityName : cityList) {

            City city = weatherApi.getWeatherData(API_KEY, cityName).execute().body();

            List<WeatherData> weatherDataList = dataMapper.updatedWeatherData(city.getWeatherDataList());
            weatherDataService.insert(weatherDataList);
            cityService.insert(city);
        }
        List<City> cities = cityService.loadSortedCities(sortQueryBuilder);
        EventBus.getDefault().post(dataMapper.loadCityWeatherForNow(cities));
    }
}
