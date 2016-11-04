package com.example.nvdovin.weatherapp;

import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.model.CityForecast;
import com.example.nvdovin.weatherapp.utils.DefaultThreadPoolExecutor;
import com.example.nvdovin.weatherapp.utils.Executor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

class ForecastPresenter {

    RetrofitFactory retrofitFactory;
    GreenDaoFactory greenDaoFactory;
    ForecastView view;

    ForecastPresenter(RetrofitFactory retrofitFactory, GreenDaoFactory greenDaoFactory, ForecastView view) {

        this.retrofitFactory = retrofitFactory;
        this.greenDaoFactory = greenDaoFactory;
        this.view = view;
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(List<CityForecast> cityForecastList) {
        view.displayData(cityForecastList);
        view.hideLoading();
    }

    void getData() {
        DefaultThreadPoolExecutor.getInstance().executeBackground(new Executor(retrofitFactory, greenDaoFactory));
    }


}
