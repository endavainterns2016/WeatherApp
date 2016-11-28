package com.example.nvdovin.weatherapp.presentation.details;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.presentation.application.WeatherApplication;
import com.example.nvdovin.weatherapp.presentation.details.adapter.MainRecyclerAdapter;
import com.example.nvdovin.weatherapp.presentation.history.HistoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    private static final String DETAIL_BUNDLE = "DETAIL_BUNDLE";
    private static final String ARGS_KEY = "HISTORY_ARGS";
    private static final int NUMBER_OF_DAYS_TO_FORECAST = 4;
    private static final String TIMESTAMP = "TIMESTAMP_KEY";
    private static final String CITY_ID = "CITY_ID_KEY";

    @BindView(R.id.detail_main_recycler_view)
    RecyclerView mainRecycler;
    @BindView(R.id.detail_main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_history_button)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.detail_main_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

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
        setContentView(R.layout.detail_main_layout);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(DETAIL_BUNDLE);
        final Long cityId = bundle.getLong(CITY_ID);
        Long timestamp = bundle.getLong(TIMESTAMP);

        DetailsPresenter detailsPresenter = new DetailsPresenter(WeatherApplication.getAppComponent().cityService(), WeatherApplication.getAppComponent().weatherDataService());

        collapsingToolbarLayout.setTitle(detailsPresenter.getCityName(cityId));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        mainRecycler.setLayoutManager(new LinearLayoutManager(this));
        mainRecycler.setVerticalFadingEdgeEnabled(true);
        mainRecycler.setAdapter(new MainRecyclerAdapter(
                this,
                detailsPresenter.getDailyForecastList(cityId, timestamp, NUMBER_OF_DAYS_TO_FORECAST),
                detailsPresenter.getCurrentWeather(cityId)
        ));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyIntent = new Intent(DetailActivity.this, HistoryActivity.class);
                Bundle sentBundle = new Bundle();
                sentBundle.putLong(CITY_ID, cityId);
                historyIntent.putExtra(ARGS_KEY, sentBundle);
                startActivity(historyIntent);
            }
        });
    }
}
