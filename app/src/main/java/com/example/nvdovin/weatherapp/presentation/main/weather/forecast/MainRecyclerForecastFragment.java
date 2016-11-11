package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.adapter.ForecastRecyclerViewAdapter;
import com.example.nvdovin.weatherapp.domain.utils.design.SeparatorDecoration;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRecyclerForecastFragment extends Fragment implements ForecastView {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.forecast_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private ForecastRecyclerViewAdapter recycleViewAdapter;
    private ForecastPresenter forecastPresenter;
    private ForecastRecyclerViewAdapter.OnItemClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_recycler_forecast_fragment, container, false);
        recyclerView = new RecyclerView(view.getContext());
        ButterKnife.bind(this, view);

        setupView();
        forecastPresenter = new ForecastPresenter(this, view.getContext());
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
        recyclerView.addItemDecoration(separatorDecoration);

        listener = new ForecastRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CityForecast cityForecast) {
                Toast.makeText(getActivity(), "You have selected " + cityForecast.getCityName() + " with the id - " + cityForecast.getCityId(), Toast.LENGTH_SHORT).show();
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
