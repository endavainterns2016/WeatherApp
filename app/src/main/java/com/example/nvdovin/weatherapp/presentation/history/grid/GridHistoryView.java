package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;
import com.example.nvdovin.weatherapp.presentation.history.grid.adapter.GridHistoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridHistoryView {

    private static final String DAY_TIMESTAMP = "TIMESTAMP_KEY";
    private static final String CITY_ID = "CITY_ID_KEY";
    private static final String DETAIL_BUNDLE = "DETAIL_BUNDLE";

    @BindView(R.id.weather_grid_view)
    GridView weatherGridView;
    @BindView(R.id.no_data)
    TextView noData;

    private Context context;
    private View view;
    private GridHistoryAdapter gridHistoryAdapter;

    public GridHistoryView(Context context, GridHistoryAdapter gridHistoryAdapter) {

        this.context = context;
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


    public void displayHistory(final List<WeatherData> updatedWeatherDataList) {
        gridHistoryAdapter.swap(updatedWeatherDataList);
        noData.setVisibility(updatedWeatherDataList.isEmpty() ? View.VISIBLE : View.GONE);
        weatherGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putLong(CITY_ID, updatedWeatherDataList.get(position).getCityId());
                bundle.putLong(DAY_TIMESTAMP, updatedWeatherDataList.get(position).getDt());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DETAIL_BUNDLE, bundle);
                context.startActivity(intent);
            }
        });
    }

}
