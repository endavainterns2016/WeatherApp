package com.example.nvdovin.weatherapp.utils;

import com.example.nvdovin.weatherapp.backend.response.GetCityListResponse;
import com.example.nvdovin.weatherapp.database.SortQueryBuilder;
import com.example.nvdovin.weatherapp.database.dao_.CityDao;
import com.example.nvdovin.weatherapp.database.model.City;
import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class Executor implements Operation {

    private RetrofitFactory retrofitFactory;
    private GreenDaoFactory greenDaoFactory;


    public Executor(RetrofitFactory retrofitFactory, GreenDaoFactory greenDaoFactory) {
        this.retrofitFactory = retrofitFactory;
        this.greenDaoFactory = greenDaoFactory;
    }

    @Override
    public void execute() throws Exception {
        final GetCityListResponse cityResponse = retrofitFactory.getCityList();
        final List<String> cityList = cityResponse.getCities();
        for (String cityName : cityList) {
            final City city = retrofitFactory.getData(cityName);
            greenDaoFactory.insert(city);
        }

        SortQueryBuilder sortByName = new SortQueryBuilder();
        sortByName.setAscending(true);
        sortByName.setProperty(CityDao.Properties.Name);
        EventBus.getDefault().post(greenDaoFactory.loadSortedCities(sortByName));
    }
}
