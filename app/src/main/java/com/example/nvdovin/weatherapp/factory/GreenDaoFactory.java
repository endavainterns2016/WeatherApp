package com.example.nvdovin.weatherapp.factory;

import android.content.Context;

import com.example.nvdovin.weatherapp.greendao.DaoMaster;
import com.example.nvdovin.weatherapp.greendao.DaoSession;
import com.example.nvdovin.weatherapp.model.City;

import java.util.List;

public class GreenDaoFactory {

    private static final String DB_NAME = "WEATHER_DB";


    private final DaoSession daoSession;

    public GreenDaoFactory(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public void insert(City city) {
        daoSession.getCityDao().insertOrReplace(city);//TODO implement uniqueId logic
    }

    public List<City> loadCities(){
       return daoSession.getCityDao().loadAll();
    }

}
