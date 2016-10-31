package com.example.nvdovin.weatherapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nvdovin.weatherapp.adapter.RecyclerViewAdapter;
import com.example.nvdovin.weatherapp.adapter.SeparatorDecoration;
import com.example.nvdovin.weatherapp.database.model.City;
import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;

import java.util.List;

public class ForecastActivity extends AppCompatActivity implements ForecastView {
    private RecyclerView recyclerView;
    int tempScale = 273;
    float separatorHeight = 5.5f;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        spinner = (ProgressBar) findViewById(R.id.forecast_progress_bar);

        RetrofitFactory retrofitFactory = new RetrofitFactory();
        GreenDaoFactory greenDaoFactory = new GreenDaoFactory(this);

        ForecastPresenter forecastPresenter = new ForecastPresenter(retrofitFactory, greenDaoFactory, this);
        forecastPresenter.getData();


    }

    @Override
    public void displayData(List<City> data) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SeparatorDecoration(this, Color.GRAY, separatorHeight));
        recyclerView.setAdapter(new RecyclerViewAdapter(data, tempScale));
    }


    @Override
    public void hideLoading() {
        spinner.setVisibility(View.GONE);
    }
}
