package com.example.nvdovin.weatherapp.presentation.details.core;


import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;
import com.example.nvdovin.weatherapp.domain.utils.navigator.OperationNavigation;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;
import com.example.nvdovin.weatherapp.presentation.details.adapter.MainRecyclerAdapter;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

public class DetailView {

    @BindView(R.id.detail_main_recycler_view)
    RecyclerView mainRecycler;
    @BindView(R.id.detail_history_button)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.detail_main_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private Context context;
    private View detailsView;
    private OperationNavigation viewCallback;
    private ImageUtils imageUtils;

    public DetailView(DetailActivity detailActivity,
                      ImageUtils imageUtils) {

        this.context = detailActivity.getApplicationContext();
        this.imageUtils = imageUtils;

        FrameLayout frameLayout = new FrameLayout(detailActivity.getApplicationContext());
        frameLayout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        detailsView = LayoutInflater.from(detailActivity).inflate(R.layout.detail_main_layout, frameLayout, true);
        ButterKnife.bind(this, detailsView);

    }

    public View getDetailsView() {
        return detailsView;
    }

    void setupView(String cityName,
                   List<DailyForecast> dailyForecastList,
                   final WeatherData weatherData,
                   SharedPrefs sharedPrefs) {

        collapsingToolbarLayout.setTitle(cityName);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

        mainRecycler.setLayoutManager(new LinearLayoutManager(context));
        mainRecycler.setVerticalFadingEdgeEnabled(true);
        mainRecycler.setAdapter(new MainRecyclerAdapter(
                context,
                dailyForecastList,
                weatherData,
                sharedPrefs,
                imageUtils
        ));

        RxView.clicks(floatingActionButton)
                .doOnNext(aVoid -> viewCallback.navigationButtonHandler())
                .subscribe();
    }



    public void setCallback(OperationNavigation viewCallback) {
        this.viewCallback = viewCallback;
    }
}

