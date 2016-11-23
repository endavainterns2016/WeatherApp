package com.example.nvdovin.weatherapp.presentation.application.dagger;


import android.content.Context;

import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefsModule {

    @Provides
    SharedPrefs provideSharedPrefs(Context context) {
        return new SharedPrefs(context);
    }
}
