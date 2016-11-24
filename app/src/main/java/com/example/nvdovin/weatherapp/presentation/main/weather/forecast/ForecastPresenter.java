package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.dao.CityDao;
import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.domain.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.executor.DefaultThreadPoolExecutor;
import com.example.nvdovin.weatherapp.domain.utils.executor.Executor;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.updater.DataMapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ForecastPresenter {
    private ForecastView view;
    private RetrofitFactory retrofitFactory;
    private SharedPrefs sharedPrefs;
    private CityService cityService;
    private WeatherDataService weatherDataService;
    private DataMapper dataMapper;

    public ForecastPresenter(CityService cityService, WeatherDataService weatherDataService, ForecastView view, Context context, DataMapper dataMapper) {
        EventBus.getDefault().register(this);
        retrofitFactory = new RetrofitFactory();
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
        this.view = view;
        this.dataMapper = dataMapper;
        sharedPrefs = new SharedPrefs(context);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(List<com.example.nvdovin.weatherapp.domain.model.CityForecast> cityForecastList) {
        view.displayData(cityForecastList);
        view.setRefreshing(false);
    }

    private void getData() {
        DefaultThreadPoolExecutor.getInstance().executeBackground(new Executor(retrofitFactory, cityService, weatherDataService, dataMapper));
    }

    public void refreshCalled() {
        sharedPrefs.setLastUpdateTime();
        getData();
    }

    private void sortData() {
        SortQueryBuilder sortByName = new SortQueryBuilder();
        sortByName.setAscending(true);
        sortByName.setProperty(CityDao.Properties.Name);
        List<City> cities = cityService.loadSortedCities(sortByName);
        setData(dataMapper.loadCityWeatherForNow(cities));
    }

    public void checkLastUpdateTime() {
        if (sharedPrefs.lastUpdateExceededLimit()) {
            getData();
            sharedPrefs.setLastUpdateTime();
        } else {
            sortData();
            view.hideLoading();
        }
    }

}
