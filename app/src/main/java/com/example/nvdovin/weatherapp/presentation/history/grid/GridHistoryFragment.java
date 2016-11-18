package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;
import com.example.nvdovin.weatherapp.presentation.history.grid.adapter.GridHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridHistoryFragment extends Fragment implements GridHistoryView {

    public static final String DAY_TIMESTAMP = "TIMESTAMP_KEY";
    public static final String CITY_ID = "CITY_ID_KEY";
    private static final String DETAIL_BUNDLE = "DETAIL_BUNDLE";

    @BindView(R.id.weather_grid_view)
    GridView weatherGridView;
    @BindView(R.id.no_data)
    TextView noData;

    private GridHistoryAdapter gridHistoryAdapter;
    private GridHistoryPresenter gridHistoryPresenter;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        timestamp = args.getLong(DAY_TIMESTAMP);
        cityId = args.getLong(CITY_ID);

        View view = inflater.inflate(R.layout.grid_history_layout, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<WeatherData> weatherDataList = new ArrayList<>();
        gridHistoryPresenter = new GridHistoryPresenter(getActivity(), this, cityId, timestamp);
        gridHistoryAdapter = new GridHistoryAdapter(weatherDataList, getActivity());
        weatherGridView.setAdapter(gridHistoryAdapter);

        gridHistoryPresenter.getWeatherData();

    }


    @Override
    public void displayHistory() {
        final List<WeatherData> updatedWeatherDataList = gridHistoryPresenter.getForecast();
        gridHistoryAdapter.swap(updatedWeatherDataList);
        if (updatedWeatherDataList.isEmpty()) {
            noData.setVisibility(View.VISIBLE);
        } else {
            noData.setVisibility(View.GONE);
        }
        weatherGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putLong(CITY_ID, updatedWeatherDataList.get(position).getCityId());
                bundle.putLong(DAY_TIMESTAMP, updatedWeatherDataList.get(position).getDt());
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DETAIL_BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }
}
