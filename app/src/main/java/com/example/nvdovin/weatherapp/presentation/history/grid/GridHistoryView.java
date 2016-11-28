package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.presentation.history.grid.adapter.GridHistoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridHistoryView {

    @BindView(R.id.weather_grid_view)
    GridView weatherGridView;
    @BindView(R.id.no_data)
    TextView noData;

    private View view;
    private GridHistoryAdapter gridHistoryAdapter;

    public GridHistoryView(Context context, GridHistoryAdapter gridHistoryAdapter) {

        this.gridHistoryAdapter = gridHistoryAdapter;

        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        view = LayoutInflater.from(context).inflate(R.layout.grid_history_layout, frameLayout, true);

        ButterKnife.bind(this, view);

        weatherGridView.setAdapter(gridHistoryAdapter);

    }

    public View getView() {
        return view;
    }

    public void displayHistory(final List<WeatherData> updatedWeatherDataList, AdapterView.OnItemClickListener itemClickListener) {
        gridHistoryAdapter.swap(updatedWeatherDataList);
        noData.setVisibility(updatedWeatherDataList.isEmpty() ? View.VISIBLE : View.GONE);
        weatherGridView.setOnItemClickListener(itemClickListener);
    }

}
