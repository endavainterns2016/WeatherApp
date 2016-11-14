package com.example.nvdovin.weatherapp.presentation.details;


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
import com.example.nvdovin.weatherapp.presentation.details.adapter.MainRecyclerAdapter;
import com.example.nvdovin.weatherapp.presentation.history.HistoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    private static final String DETAIL_BUNDLE = "DETAIL_BUNDLE";
    private static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";
    private static final String CITY_ID_KEY = "CITY_ID_KEY";
    private static final int NUMBER_OF_DAYS_TO_FORECAST = 4;

    @BindView(R.id.detail_main_recycler_view)
    RecyclerView mainRecycler;
    @BindView(R.id.detail_main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_history_button)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.detail_main_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_main_layout);
        getSupportActionBar().hide();

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(DETAIL_BUNDLE);
        Long cityId = bundle.getLong(CITY_ID_KEY);
        Long timestamp = bundle.getLong(TIMESTAMP_KEY);

        DetailsPresenter detailsPresenter = new DetailsPresenter(this);

        toolbar.setTitle(detailsPresenter.getCityName(cityId));

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        collapsingToolbarLayout.setTitle(detailsPresenter.getCityName(cityId));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        mainRecycler.setLayoutManager(new LinearLayoutManager(this));
        mainRecycler.setAdapter(new MainRecyclerAdapter(
                this,
                detailsPresenter.getDailyForecastList(cityId, timestamp, NUMBER_OF_DAYS_TO_FORECAST),
                detailsPresenter.getCurrentWeather(cityId)
        ));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
