package com.example.nvdovin.weatherapp.presentation.main.weather.history.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.presentation.main.weather.history.grid.adapter.GridHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridHistoryFragment extends Fragment implements GridHistoryView {

    private GridView weatherGridView;
    private GridHistoryAdapter gridHistoryAdapter;
    private List<WeatherData> weatherDataList;
    private GridHistoryPresenter gridHistoryPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        gridHistoryPresenter = new GridHistoryPresenter(getActivity(), this, 618426L, 1478800800L);
        gridHistoryAdapter = new GridHistoryAdapter(weatherDataList, getActivity());
        weatherGridView.setAdapter(gridHistoryAdapter);

        gridHistoryPresenter.getWeatherData();

    }


    @Override
    public void displayHistory() {
        gridHistoryAdapter.swap(gridHistoryPresenter.getForecast());
    }
}
