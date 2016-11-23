package com.example.nvdovin.weatherapp.domain.utils.executor;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.dao.CityDao;
import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.data.network.response.GetCityListResponse;
import com.example.nvdovin.weatherapp.domain.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.updater.DataMapper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class Executor implements Operation {

    private RetrofitFactory retrofitFactory;
    private CityService cityService;
    private WeatherDataService weatherDataService;
    private DataMapper dataMapper;

    public Executor(RetrofitFactory retrofitFactory,
                    CityService cityService,
                    WeatherDataService weatherDataService,
                    DataMapper dataMapper) {
        this.retrofitFactory = retrofitFactory;
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
        this.dataMapper = dataMapper;
    }

    @Override
    public void execute() throws Exception {
        final GetCityListResponse cityResponse = retrofitFactory.getCityList();
        final List<String> cityList = cityResponse.getCities();
        for (String cityName : cityList) {
            City city = retrofitFactory.getData(cityName);
            List<WeatherData> weatherDataList = dataMapper.updatedWeatherData(city.getWeatherDataList());
            weatherDataService.insert(weatherDataList);
            cityService.insert(city);
        }

        SortQueryBuilder sortByName = new SortQueryBuilder();
        sortByName.setAscending(true);
        sortByName.setProperty(CityDao.Properties.Name);
        EventBus.getDefault().post(cityService.loadSortedCities(sortByName));

    }
}
