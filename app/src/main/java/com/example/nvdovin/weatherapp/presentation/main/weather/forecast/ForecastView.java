package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;
import com.example.nvdovin.weatherapp.domain.utils.design.SeparatorDecoration;
import com.example.nvdovin.weatherapp.domain.utils.design.TypedValueWrapper;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.adapter.ForecastRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastView {

    private static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";
    private static final String CITY_ID_KEY = "CITY_ID_KEY";
    private static final String DETAIL_BUNDLE = "DETAIL_BUNDLE";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.forecast_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.forecast_progress_bar)
    ProgressBar progressBar;

    private ForecastRecyclerViewAdapter recycleViewAdapter;
    private View view;
    private Context context;
    private SharedPrefs sharedPrefs;
    private ImageUtils imageUtils;


    public ForecastView(ForecastFragment forecastFragment,
                        SharedPrefs sharedPrefs,
                        ImageUtils imageUtils) {

        context = forecastFragment.getActivity();
        this.sharedPrefs = sharedPrefs;
        this.imageUtils = imageUtils;

        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        view = LayoutInflater.from(context).inflate(R.layout.main_recycler_forecast_fragment, frameLayout, true);

        ButterKnife.bind(this, view);

        setupView();

    }

    public View getView() {
        return view;
    }

    private void setupView() {

        TypedValue outValue = new TypedValue();
        context.getResources().getValue(R.dimen.separator_height, outValue, true);
        float separatorHeight = outValue.getFloat();
        SeparatorDecoration separatorDecoration = new SeparatorDecoration(context, Color.GRAY, separatorHeight, new Paint(), new TypedValueWrapper());

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(separatorDecoration);

        final ForecastRecyclerViewAdapter.OnItemClickListener listener = new ForecastRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CityForecast cityForecast) {
                Bundle bundle = new Bundle();
                bundle.putLong(CITY_ID_KEY, cityForecast.getCityId());
                bundle.putLong(TIMESTAMP_KEY, TimeUtils.getCurrentTime());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DETAIL_BUNDLE, bundle);
                context.startActivity(intent);
            }
        };


        recycleViewAdapter = new ForecastRecyclerViewAdapter(new ArrayList<CityForecast>(),
                listener,
                context,
                sharedPrefs,
                imageUtils);
        recyclerView.setAdapter(recycleViewAdapter);
    }

    public void onRefresh(final OnRefreshListener refreshListener) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshListener.setOnRefreshListener();
            }
        });
    }

    public void displayData(List<CityForecast> data) {
        recycleViewAdapter.swap(data);
    }

    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }


    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

}
