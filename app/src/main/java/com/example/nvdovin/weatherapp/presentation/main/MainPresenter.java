package com.example.nvdovin.weatherapp.presentation.main;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.dao.CityDao;
import com.example.nvdovin.weatherapp.domain.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.domain.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.utils.executor.DefaultThreadPoolExecutor;
import com.example.nvdovin.weatherapp.domain.utils.executor.Executor;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MainPresenter {

    private RetrofitFactory retrofitFactory;
    private GreenDaoFactory greenDaoFactory;
    private MainView view;
    private SharedPrefs sharedPrefs;


    public MainPresenter(MainView view, Context context) {
        this.view = view;
        EventBus.getDefault().register(this);
        greenDaoFactory = new GreenDaoFactory(context);
        retrofitFactory = new RetrofitFactory();
        sharedPrefs = new SharedPrefs(context);
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
        DefaultThreadPoolExecutor.getInstance().executeBackground(new Executor(retrofitFactory, greenDaoFactory));
    }
}