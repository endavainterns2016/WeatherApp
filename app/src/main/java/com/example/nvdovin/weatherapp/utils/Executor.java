package com.example.nvdovin.weatherapp.utils;

import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.model.City;

import org.greenrobot.eventbus.EventBus;


public class Executor implements Operation {

    private RetrofitFactory retrofitFactory;
    private GreenDaoFactory greenDaoFactory;


    public Executor(RetrofitFactory retrofitFactory, GreenDaoFactory greenDaoFactory){
        this.retrofitFactory = retrofitFactory;
        this.greenDaoFactory = greenDaoFactory;
    }

    @Override
    public void execute() throws Exception {
        final City city = retrofitFactory.getData("Chisinau");//TODO implement basic logic
        greenDaoFactory.insert(city);
        EventBus.getDefault().post(greenDaoFactory.loadCities());
    }
}
