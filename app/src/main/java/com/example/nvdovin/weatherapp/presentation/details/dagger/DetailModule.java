package com.example.nvdovin.weatherapp.presentation.details.dagger;

import android.content.Context;

import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;
import com.example.nvdovin.weatherapp.presentation.details.core.DetailsPresenter;
import com.example.nvdovin.weatherapp.presentation.details.adapter.MainRecyclerAdapter;
import com.example.nvdovin.weatherapp.presentation.details.core.DetailView;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule {

    private DetailActivity detailActivity;

    public DetailModule(DetailActivity detailActivity) {
        this.detailActivity = detailActivity;
    }

    @Provides
    @DetailScope
    DetailsPresenter detailsPresenter(CityService cityService,
                                      WeatherDataService weatherDataService,
                                      DataMapper dataMapper,
                                      DetailView detailView,
                                      Context context,
                                      SharedPrefs sharedPrefs,
                                      Navigator.Builder builder) {
        return new DetailsPresenter(cityService, weatherDataService, dataMapper, detailView, builder, sharedPrefs);
    }

    @Provides
    @DetailScope
    DetailView provideDetailsView(ImageUtils imageUtils) {
        return new DetailView(detailActivity, imageUtils);
    }
}
