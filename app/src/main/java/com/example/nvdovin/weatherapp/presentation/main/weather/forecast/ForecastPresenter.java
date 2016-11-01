package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.dao.CityDao;
import com.example.nvdovin.weatherapp.domain.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.domain.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.domain.utils.executor.DefaultThreadPoolExecutor;
import com.example.nvdovin.weatherapp.domain.utils.executor.Executor;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ForecastPresenter {
    private ForecastView forecastView;
    private RetrofitFactory retrofitFactory;
    private GreenDaoFactory greenDaoFactory;
    private SharedPrefs sharedPrefs;

    public ForecastPresenter(ForecastView view, Context context) {
        EventBus.getDefault().register(this);
        greenDaoFactory = new GreenDaoFactory(context);
        retrofitFactory = new RetrofitFactory();
        forecastView = view;
        sortData();
        sharedPrefs = new SharedPrefs(context);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(List<com.example.nvdovin.weatherapp.domain.model.CityForecast> cityForecastList) {
        forecastView.displayData(cityForecastList);
        forecastView.setRefreshing(false);
    }

    private void getData() {
        DefaultThreadPoolExecutor.getInstance().executeBackground(new Executor(retrofitFactory, greenDaoFactory));
    }

    public void refreshCalled() {
        sharedPrefs.setLastUpdateTime();
        getData();
    }

    private void sortData() {
        SortQueryBuilder sortByName = new SortQueryBuilder();
        sortByName.setAscending(true);
        sortByName.setProperty(CityDao.Properties.Name);
        setData(greenDaoFactory.loadSortedCities(sortByName));
    }
}
