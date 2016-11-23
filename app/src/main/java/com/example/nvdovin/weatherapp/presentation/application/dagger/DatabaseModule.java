package com.example.nvdovin.weatherapp.presentation.application.dagger;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.dao.DaoMaster;
import com.example.nvdovin.weatherapp.data.dao.DaoSession;

import javax.inject.Singleton;

import dagger.Provides;

public class DatabaseModule {

    private static final String DB_NAME = "WEATHER_DB";

    @Provides
    @Singleton
    public DaoSession provideDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        return daoMaster.newSession();
    }

}
