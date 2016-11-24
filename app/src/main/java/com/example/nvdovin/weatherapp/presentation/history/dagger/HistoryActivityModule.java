package com.example.nvdovin.weatherapp.presentation.history.dagger;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.nvdovin.weatherapp.presentation.history.HistoryActivity;
import com.example.nvdovin.weatherapp.presentation.history.adapter.HistoryViewPagerAdapter;
import com.example.nvdovin.weatherapp.presentation.history.model.CityDate;

import dagger.Module;
import dagger.Provides;

@Module
public class HistoryActivityModule {

    HistoryActivity historyActivity;

    public HistoryActivityModule(HistoryActivity historyActivity) {
        this.historyActivity = historyActivity;
    }

    @Provides
    @HistoryActivityScope
    HistoryActivity provideHistoryActivity(){
        return historyActivity;
    }

    @Provides
    @HistoryActivityScope
    FragmentManager provideSupportFragmentManager(){
        return historyActivity.getSupportFragmentManager();
    }
    @Provides
    @HistoryActivityScope
    CityDate provideCityDate(){
        return new CityDate();
    }

    @Provides
    @HistoryActivityScope
    HistoryViewPagerAdapter provideHistoryViewPagerAdapter(FragmentManager fragmentManager, Context context){
        return new HistoryViewPagerAdapter(fragmentManager, context);
    }


}
