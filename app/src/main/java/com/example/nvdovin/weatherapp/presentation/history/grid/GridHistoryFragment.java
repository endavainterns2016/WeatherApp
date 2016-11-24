package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.presentation.application.WeatherApplication;
import com.example.nvdovin.weatherapp.presentation.history.grid.adapter.GridHistoryAdapter;
import com.example.nvdovin.weatherapp.presentation.history.grid.dagger.DaggerGridHistoryFragmentComponent;
import com.example.nvdovin.weatherapp.presentation.history.grid.dagger.GridHistoryFragmentModule;

import javax.inject.Inject;

public class GridHistoryFragment extends Fragment {

    private static final String DAY_TIMESTAMP = "TIMESTAMP_KEY";
    private static final String CITY_ID = "CITY_ID_KEY";
    @Inject
    CityService cityService;
    @Inject
    WeatherDataService weatherDataService;
    @Inject
    GridHistoryAdapter gridHistoryAdapter;
    @Inject
    GridHistoryPresenter gridHistoryPresenter;
    @Inject
    GridHistoryView gridHistoryView;

    private Long timestamp;
    private Long cityId;

    public GridHistoryFragment() {

    }

    public static GridHistoryFragment newInstance(Long timestamp, Long cityId) {
        GridHistoryFragment gridHistoryFragment = new GridHistoryFragment();
        Bundle args = new Bundle();
        args.putLong(DAY_TIMESTAMP, timestamp);
        args.putLong(CITY_ID, cityId);
        gridHistoryFragment.setArguments(args);
        return gridHistoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        timestamp = args.getLong(DAY_TIMESTAMP);
        cityId = args.getLong(CITY_ID);

        DaggerGridHistoryFragmentComponent.builder()
                .appComponent(WeatherApplication.getAppComponent())
                .gridHistoryFragmentModule(new GridHistoryFragmentModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return gridHistoryView.getView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridHistoryPresenter.setCityId(cityId);
        gridHistoryPresenter.setTimestamp(timestamp);

        gridHistoryPresenter.getWeatherData();

    }

}
