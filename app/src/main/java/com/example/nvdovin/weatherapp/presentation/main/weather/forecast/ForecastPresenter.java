package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.utils.executor.DefaultThreadPoolExecutor;
import com.example.nvdovin.weatherapp.domain.utils.executor.Executor;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ForecastPresenter {
    private ForecastView view;
    private SharedPrefs sharedPrefs;
    private CityService cityService;
    private Executor executor;
    private DataMapper dataMapper;
    private DefaultThreadPoolExecutor defaultThreadPoolExecutor;
    private SortQueryBuilder sortQueryBuilder;

    public ForecastPresenter(Executor executor,
                             CityService cityService,
                             ForecastView view,
                             SharedPrefs sharedPrefs,
                             DataMapper dataMapper,
                             DefaultThreadPoolExecutor defaultThreadPoolExecutor,
                             SortQueryBuilder sortQueryBuilder) {
        EventBus.getDefault().register(this);
        this.executor = executor;
        this.cityService = cityService;
        this.view = view;
        this.sharedPrefs = sharedPrefs;
        this.dataMapper = dataMapper;
        this.defaultThreadPoolExecutor = defaultThreadPoolExecutor;
        this.sortQueryBuilder = sortQueryBuilder;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(List<com.example.nvdovin.weatherapp.domain.model.CityForecast> cityForecastList) {
        view.displayData(cityForecastList);
        view.setRefreshing(false);
    }

    private void getData() {
        defaultThreadPoolExecutor.executeBackground(executor);
    }

    public void refreshCalled() {
        sharedPrefs.setLastUpdateTime();
        getData();
    }

    private void sortData() {
        List<City> cities = cityService.loadSortedCities(sortQueryBuilder);
        setData(dataMapper.loadCityWeatherForNow(cities));
    }

    public void checkLastUpdateTime() {
        if (sharedPrefs.lastUpdateExceededLimit()) {
            getData();
            sharedPrefs.setLastUpdateTime();
        } else {
            sortData();
        }
        view.hideLoading();
    }

}
