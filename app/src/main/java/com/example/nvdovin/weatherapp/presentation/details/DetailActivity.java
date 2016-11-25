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
    private static final String DETAIL_BUNDLE = "DETAIL_BUNDLE";
    private static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";
    private static final String CITY_ID_KEY = "CITY_ID_KEY";

    @Inject
    DetailsPresenter detailsPresenter;
    @Inject
    DetailView detailView;

    public static void start(Context context, WeatherData weatherData) {
        Bundle bundle = new Bundle();
        bundle.putLong(CITY_ID, weatherData.getCityId());
        bundle.putLong(TIMESTAMP, weatherData.getDt());
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DETAIL_BUNDLE, bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerDetailComponent.builder()
                .appComponent(WeatherApplication.getAppComponent())
                .detailModule(new DetailModule(this))
                .build()
                .inject(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(DETAIL_BUNDLE);

        setContentView(detailView.getDetailsView());

        detailsPresenter.setupDetailView(bundle.getLong(CITY_ID_KEY), bundle.getLong(TIMESTAMP_KEY));

        detailView.setCallback(detailsPresenter.getCallBack());

    }
}
