package com.example.nvdovin.weatherapp.presentation.details.dagger;


import com.example.nvdovin.weatherapp.presentation.application.dagger.AppComponent;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;

import dagger.Component;

@Component(modules = DetailModule.class, dependencies = AppComponent.class)
@DetailScope
public interface DetailComponent {
    void inject(DetailActivity detailActivity);
}
