package com.example.nvdovin.weatherapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nvdovin.weatherapp.adapter.RecyclerViewAdapter;
import com.example.nvdovin.weatherapp.adapter.SeparatorDecoration;
import com.example.nvdovin.weatherapp.model.CityForecast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastActivity extends AppCompatActivity implements ForecastView {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.forecast_swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.forecast_progress_bar) ProgressBar progressBar;

    private ForecastPresenter forecastPresenter;
    private RecyclerViewAdapter recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        forecastPresenter = new ForecastPresenter(this, this);

        setupView();
        forecastPresenter.checkLastUpdateTime();
    }

    private void setupView() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                forecastPresenter.refreshCalled();
            }
        });

        TypedValue outValue = new TypedValue();
        getResources().getValue(R.dimen.separator_height, outValue, true);
        float separatorHeight = outValue.getFloat();
        SeparatorDecoration separatorDecoration = new SeparatorDecoration(this, Color.GRAY, separatorHeight);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(separatorDecoration);
        recycleViewAdapter = new RecyclerViewAdapter(new ArrayList<CityForecast>(), this);
        recyclerView.setAdapter(recycleViewAdapter);
    }

    @Override
    public void displayData(List<CityForecast> data) {
        recycleViewAdapter.swap(data);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

}
