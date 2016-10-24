package com.example.nvdovin.weatherapp;

import com.example.nvdovin.weatherapp.Utils.DefaultThreadPoolExecutor;
import com.example.nvdovin.weatherapp.Utils.Executor;
import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.model.City;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MainPresenter {

    RetrofitFactory retrofitFactory;
    GreenDaoFactory greenDaoFactory;
    MainView view;

    public MainPresenter(RetrofitFactory retrofitFactory, GreenDaoFactory greenDaoFactory, MainView view){

        this.retrofitFactory = retrofitFactory;
        this.greenDaoFactory = greenDaoFactory;
        this.view = view;
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(List<City> citiesList){
        view.displayData(citiesList);
        view.hideLoading();
    }

    public void getData(){
        view.showLoading();
        DefaultThreadPoolExecutor.getInstance().executeBackground(new Executor(retrofitFactory, greenDaoFactory));

    }


}
