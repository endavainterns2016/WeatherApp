package com.example.nvdovin.weatherapp.Utils;

import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.model.FirstModel;

import org.greenrobot.eventbus.EventBus;


public class Executor implements Operation {

    RetrofitFactory retrofitFactory;
    GreenDaoFactory greenDaoFactory;


    public Executor(RetrofitFactory retrofitFactory, GreenDaoFactory greenDaoFactory){
        this.retrofitFactory = retrofitFactory;
        this.greenDaoFactory = greenDaoFactory;
    }

    @Override
    public void execute() throws Exception {
        final FirstModel firstModel = retrofitFactory.getData("Chisinau");//TODO implement basic logic
        greenDaoFactory.insert(firstModel);
        EventBus.getDefault().post(greenDaoFactory.loadCities());
    }
}
