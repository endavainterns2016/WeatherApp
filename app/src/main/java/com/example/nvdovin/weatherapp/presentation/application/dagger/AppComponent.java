package com.example.nvdovin.weatherapp.presentation.application.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    Context context();

}
