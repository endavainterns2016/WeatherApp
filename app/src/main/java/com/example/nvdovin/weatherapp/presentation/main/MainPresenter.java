package com.example.nvdovin.weatherapp.presentation.main;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.dao.CityDao;
import com.example.nvdovin.weatherapp.domain.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
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

public class MainPresenter {

    private RetrofitFactory retrofitFactory;
    private MainView view;
    private SharedPrefs sharedPrefs;
    private CityService cityService;
    private WeatherDataService weatherDataService;
    private DataMapper dataMapper;

    public MainPresenter(CityService cityService,
                         WeatherDataService weatherDataService,
                         MainView view,
                         Context context,
                         DataMapper dataMapper) {
        this.view = view;
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
        EventBus.getDefault().register(this);
        retrofitFactory = new RetrofitFactory();
        sharedPrefs = new SharedPrefs(context);
        this.dataMapper = dataMapper;
    }

    public void checkLastUpdateTime() {
        if (sharedPrefs.lastUpdateExceededLimit()) {
            getData();
            sharedPrefs.setLastUpdateTime();
        } else {
            SortQueryBuilder sortByName = new SortQueryBuilder();
            sortByName.setAscending(true);
            sortByName.setProperty(CityDao.Properties.Name);
            view.hideLoading();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(List<CityForecast> cityForecastList) {
        view.hideLoading();
    }

    public void getData() {
        DefaultThreadPoolExecutor.getInstance()
                .executeBackground(new Executor(retrofitFactory, cityService,
                        weatherDataService, dataMapper));
    }
}