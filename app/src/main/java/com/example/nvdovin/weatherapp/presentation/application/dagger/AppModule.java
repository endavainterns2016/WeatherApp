package com.example.nvdovin.weatherapp.presentation.application.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context appContext;

    public AppModule(@NonNull Context context) {
        appContext = context;
    }


    @Provides
    Context provideContext() {
        return appContext;
    }

}
