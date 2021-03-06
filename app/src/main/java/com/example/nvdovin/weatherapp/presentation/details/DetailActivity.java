package com.example.nvdovin.weatherapp.presentation.details;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nvdovin.weatherapp.presentation.application.WeatherApplication;
import com.example.nvdovin.weatherapp.presentation.details.core.DetailView;
import com.example.nvdovin.weatherapp.presentation.details.core.DetailsPresenter;
import com.example.nvdovin.weatherapp.presentation.details.dagger.DaggerDetailComponent;
import com.example.nvdovin.weatherapp.presentation.details.dagger.DetailModule;

import javax.inject.Inject;

public class DetailActivity extends AppCompatActivity {
    private static final String ARGS_KEY = "HISTORY_ARGS";
    private static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";
    private static final String CITY_ID_KEY = "CITY_ID_KEY";

    @Inject
    DetailsPresenter detailsPresenter;
    @Inject
    DetailView detailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        DaggerDetailComponent.builder()
                .appComponent(WeatherApplication.getAppComponent())
                .detailModule(new DetailModule(this))
                .build()
                .inject(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(ARGS_KEY);

        setContentView(detailView.getDetailsView());

        detailsPresenter.setupDetailView(bundle.getLong(CITY_ID_KEY), bundle.getLong(TIMESTAMP_KEY));

        detailView.setCallback(detailsPresenter.getCallBack());

    }
}
