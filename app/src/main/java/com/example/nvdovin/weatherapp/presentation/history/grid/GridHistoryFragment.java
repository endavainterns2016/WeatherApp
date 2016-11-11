package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.presentation.history.grid.adapter.GridHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridHistoryFragment extends Fragment implements GridHistoryView {

    public static final String DAY_TIMESTAMP = "DAY_TIMESTAMP";
    public static final String CITY_ID = "CITY_ID";
    private GridView weatherGridView;
    private GridHistoryAdapter gridHistoryAdapter;
    private List<WeatherData> weatherDataList;
    private GridHistoryPresenter gridHistoryPresenter;
    private Long timestamp;
    private Long cityId;

    public GridHistoryFragment() {

    }

    public static GridHistoryFragment newInstance(Long timestamp, Long cityId) {
        GridHistoryFragment testFragment = new GridHistoryFragment();
        Bundle args = new Bundle();
        args.putLong(DAY_TIMESTAMP, timestamp);
        args.putLong(CITY_ID, cityId);
        testFragment.setArguments(args);
        return testFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        timestamp = args.getLong(DAY_TIMESTAMP);
        cityId = args.getLong(CITY_ID);
        return inflater.inflate(R.layout.grid_history_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherGridView = (GridView) view.findViewById(R.id.weather_grid_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weatherDataList = new ArrayList<>();
        gridHistoryPresenter = new GridHistoryPresenter(getActivity(), this, cityId, timestamp);
        gridHistoryAdapter = new GridHistoryAdapter(weatherDataList, getActivity());
        weatherGridView.setAdapter(gridHistoryAdapter);

        gridHistoryPresenter.getWeatherData();

    }


    @Override
    public void displayHistory() {
        gridHistoryAdapter.swap(gridHistoryPresenter.getForecast());
    }
}
