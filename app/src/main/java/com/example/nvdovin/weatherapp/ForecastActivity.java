package com.example.nvdovin.weatherapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nvdovin.weatherapp.adapter.RecyclerViewAdapter;
import com.example.nvdovin.weatherapp.adapter.SeparatorDecoration;
import com.example.nvdovin.weatherapp.database.model.City;
import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ForecastActivity extends AppCompatActivity implements ForecastView {
    private ProgressBar progressBar;
    private RecyclerViewAdapter recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.forecast_progress_bar);

        Constants constants = new Constants();
        int CELSIUS_SCALE = constants.getCelsiusScale();
        List<City> cityList = new ArrayList<City>();

        TypedValue outValue = new TypedValue();

        getResources().getValue(R.dimen.separator_height, outValue, true);

        float separatorHeight = outValue.getFloat();
        SeparatorDecoration separatorDecoration = new SeparatorDecoration(this, Color.GRAY, separatorHeight);
        RetrofitFactory retrofitFactory = new RetrofitFactory();
        GreenDaoFactory greenDaoFactory = new GreenDaoFactory(this);

        ForecastPresenter forecastPresenter = new ForecastPresenter(retrofitFactory, greenDaoFactory, this);
        forecastPresenter.getData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(separatorDecoration);
        recycleViewAdapter = new RecyclerViewAdapter(cityList, CELSIUS_SCALE, this);
        recyclerView.setAdapter(recycleViewAdapter);

    }

    @Override
    public void displayData(List<City> data) {
        recycleViewAdapter.swap(data);
    }


    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
