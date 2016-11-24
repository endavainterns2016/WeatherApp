package com.example.nvdovin.weatherapp.presentation.history.dagger;

import com.example.nvdovin.weatherapp.presentation.application.dagger.AppComponent;
import com.example.nvdovin.weatherapp.presentation.history.HistoryActivity;

import dagger.Component;

@Component(modules = HistoryActivityModule.class, dependencies = AppComponent.class)
@HistoryActivityScope
public interface HistoryActivityComponent {

    void inject(HistoryActivity historyActivity);

}
