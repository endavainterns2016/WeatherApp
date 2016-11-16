package com.example.nvdovin.weatherapp;

import android.content.Context;

import com.example.nvdovin.weatherapp.database.SortQueryBuilder;
import com.example.nvdovin.weatherapp.database.dao_.CityDao;
import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.model.CityForecast;
import com.example.nvdovin.weatherapp.utils.DefaultThreadPoolExecutor;
import com.example.nvdovin.weatherapp.utils.Executor;
import com.example.nvdovin.weatherapp.utils.sharedpreferences.SharedPrefs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ForecastPresenter {

    private RetrofitFactory retrofitFactory;
    private GreenDaoFactory greenDaoFactory;
    private ForecastView view;
    private SharedPrefs sharedPrefs;

    public ForecastPresenter(ForecastView view, Context context) {
        this.view = view;
        EventBus.getDefault().register(this);
        sharedPrefs = new SharedPrefs(context);
        greenDaoFactory = new GreenDaoFactory(context);
        retrofitFactory = new RetrofitFactory();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(List<CityForecast> cityForecastList) {
        view.displayData(cityForecastList);
        view.hideLoading();
    }

    public void getData() {
        DefaultThreadPoolExecutor.getInstance().executeBackground(new Executor(retrofitFactory, greenDaoFactory));
    }

    public void refreshCalled() {
        view.setRefreshing(true);
        sharedPrefs.setLastUpdateTime();
        getData();
    }

    public void checkLastUpdateTime() {
        if (sharedPrefs.lastUpdateExceededLimit()) {
            getData();
            sharedPrefs.setLastUpdateTime();
        } else {
            SortQueryBuilder sortByName = new SortQueryBuilder();
            sortByName.setAscending(true);
            sortByName.setProperty(CityDao.Properties.Name);
            view.displayData(greenDaoFactory.loadSortedCities(sortByName));
            view.hideLoading();
        }
    }

}
