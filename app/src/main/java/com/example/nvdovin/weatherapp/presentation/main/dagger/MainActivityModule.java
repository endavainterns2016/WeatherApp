package com.example.nvdovin.weatherapp.presentation.main.dagger;

import android.content.Context;

import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.updater.DataMapper;
import com.example.nvdovin.weatherapp.presentation.main.MainActivity;
import com.example.nvdovin.weatherapp.presentation.main.MainPresenter;
import com.example.nvdovin.weatherapp.presentation.main.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainActivity mainActivity;
    private MainView mainView;

    public MainActivityModule(MainActivity mainActivity, MainView mainView) {
        this.mainActivity = mainActivity;
        this.mainView = mainView;
    }

    @Provides
    @MainActivityScope
    public MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainPresenter provideMainPresenter(CityService cityService,
                                              WeatherDataService weatherDataService,
                                              Context context,
                                              DataMapper dataMapper) {
        return new MainPresenter(cityService, weatherDataService, mainView, context, dataMapper);
    }

}
