package com.example.nvdovin.weatherapp.presentation.application.dagger;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.nvdovin.weatherapp.domain.utils.eventbus.EventBusWrapper;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context appContext;

    public AppModule(@NonNull Context context) {
        appContext = context;
    }


    @Provides
    @AppScope
    Context provideContext() {
        return appContext;
    }

    @Provides
    @AppScope
    EventBusWrapper provideEventBusWrapper() {
        return new EventBusWrapper();
    }

}
