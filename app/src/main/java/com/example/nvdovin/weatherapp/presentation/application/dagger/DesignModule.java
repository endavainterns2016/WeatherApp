package com.example.nvdovin.weatherapp.presentation.application.dagger;


import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;

import dagger.Module;
import dagger.Provides;

@Module
public class DesignModule {

    @AppScope
    @Provides
    ImageUtils provideImageUtils(){
        return new ImageUtils();
    }
}
