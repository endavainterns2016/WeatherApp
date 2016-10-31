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

import java.util.List;

public class ForecastActivity extends AppCompatActivity implements ForecastView {
    static final int CELSIUS_SCALE = 273;
    float separatorHeight;
    ProgressBar progressBar;
    TypedValue outValue;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.forecast_progress_bar);
        outValue = new TypedValue();

        getResources().getValue(R.dimen.separator_height, outValue, true);
        separatorHeight = outValue.getFloat();

        RetrofitFactory retrofitFactory = new RetrofitFactory();
        GreenDaoFactory greenDaoFactory = new GreenDaoFactory(this);

        ForecastPresenter forecastPresenter = new ForecastPresenter(retrofitFactory, greenDaoFactory, this);
        forecastPresenter.getData();


    }

    @Override
    public void displayData(List<City> data) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SeparatorDecoration(this, Color.GRAY, separatorHeight));
        recyclerView.setAdapter(new RecyclerViewAdapter(data, CELSIUS_SCALE, this));
    }


    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
