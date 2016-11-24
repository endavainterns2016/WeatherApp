package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.design.SeparatorDecoration;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.adapter.ForecastRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRecyclerForecastFragment extends Fragment implements ForecastView {
    public static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";
    public static final String CITY_ID_KEY = "CITY_ID_KEY";
    private static final String DETAIL_BUNDLE = "DETAIL_BUNDLE";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.forecast_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Inject
    CityService cityService;
    @Inject
    WeatherDataService weatherDataService;
    private ForecastRecyclerViewAdapter recycleViewAdapter;
    private ForecastPresenter forecastPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_recycler_forecast_fragment, container, false);

        ButterKnife.bind(this, view);

        setupView();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                forecastPresenter.refreshCalled();
            }
        });
        return view;
    }

    private void setupView() {

        TypedValue outValue = new TypedValue();
        getResources().getValue(R.dimen.separator_height, outValue, true);
        float separatorHeight = outValue.getFloat();
        SeparatorDecoration separatorDecoration = new SeparatorDecoration(getActivity(), Color.GRAY, separatorHeight);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setVerticalFadingEdgeEnabled(true);
        recyclerView.addItemDecoration(separatorDecoration);

        ForecastRecyclerViewAdapter.OnItemClickListener listener = new ForecastRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CityForecast cityForecast) {
                Bundle bundle = new Bundle();
                bundle.putLong(CITY_ID_KEY, cityForecast.getCityId());
                bundle.putLong(TIMESTAMP_KEY, TimeUtils.getCurrentTime());
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DETAIL_BUNDLE, bundle);
                startActivity(intent);
            }
        };

        recycleViewAdapter = new ForecastRecyclerViewAdapter(new ArrayList<CityForecast>(), listener, getActivity());
        recyclerView.setAdapter(recycleViewAdapter);
    }

    @Override
    public void displayData(List<CityForecast> data) {
        recycleViewAdapter.swap(data);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }
}
