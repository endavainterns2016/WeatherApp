package com.example.nvdovin.weatherapp.presentation.main.dagger;

import com.example.nvdovin.weatherapp.presentation.application.dagger.AppComponent;
import com.example.nvdovin.weatherapp.presentation.main.MainActivity;

import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

}
