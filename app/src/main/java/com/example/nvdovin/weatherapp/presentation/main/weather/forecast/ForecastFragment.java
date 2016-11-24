package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nvdovin.weatherapp.presentation.application.WeatherApplication;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.dagger.DaggerForecastFragmentComponent;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.dagger.ForecastFragmentModule;

import javax.inject.Inject;

public class ForecastFragment extends Fragment implements OnRefreshListener {

    @Inject
    ForecastView forecastFragmentView;
    @Inject
    ForecastPresenter forecastPresenter;

    public static ForecastFragment newInstance() {
        return new ForecastFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerForecastFragmentComponent.builder()
                .appComponent(WeatherApplication.getAppComponent())
                .forecastFragmentModule(new ForecastFragmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        forecastFragmentView.onRefresh(this);
        return forecastFragmentView.getView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        forecastPresenter.checkLastUpdateTime();
    }

    @Override
    public void setOnRefreshListener() {
        forecastPresenter.refreshCalled();
    }
}
