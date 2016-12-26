package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
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
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.adapter.ForecastRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.forecast_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.forecast_progress_bar)
    ProgressBar progressBar;

    private ForecastRecyclerViewAdapter recycleViewAdapter;
    private View view;
    private Activity activity;
    private SharedPrefs sharedPrefs;
    private ImageUtils imageUtils;
    private ViewPresenterNavigation viewPresenterNavigation;


    public ForecastView(ForecastFragment forecastFragment,
                        SharedPrefs sharedPrefs,
                        ImageUtils imageUtils) {

        activity = forecastFragment.getActivity();
        this.sharedPrefs = sharedPrefs;
        this.imageUtils = imageUtils;

        FrameLayout frameLayout = new FrameLayout(activity);
        frameLayout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        view = LayoutInflater.from(activity).inflate(R.layout.main_recycler_forecast_fragment, frameLayout, true);

        ButterKnife.bind(this, view);

        setupView();

    }

    public View getView() {
        return view;
    }

    private void setupView() {

        TypedValue outValue = new TypedValue();
        activity.getResources().getValue(R.dimen.separator_height, outValue, true);
        float separatorHeight = outValue.getFloat();
        SeparatorDecoration separatorDecoration = new SeparatorDecoration(activity, Color.GRAY, separatorHeight, new Paint(), new TypedValueWrapper());

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(separatorDecoration);

        final ForecastRecyclerViewAdapter.OnItemClickListener listener = new ForecastRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CityForecast cityForecast) {
                viewPresenterNavigation.navigationButtonHandler(cityForecast.getCityId());
            }
        };


        recycleViewAdapter = new ForecastRecyclerViewAdapter(new ArrayList<>(),
                listener,
                activity,
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

    public void setOperationNavigation(ViewPresenterNavigation viewPresenterNavigation) {
        this.viewPresenterNavigation = viewPresenterNavigation;
    }

    public void showErrorDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder
                .setTitle(activity.getString(R.string.no_internet_connection))
                .setMessage(activity.getString(R.string.close_app_quesion))
                .setPositiveButton(activity.getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.moveTaskToBack(true);
                        activity.finish();
                    }
                });

        builder.show();

    }

}
