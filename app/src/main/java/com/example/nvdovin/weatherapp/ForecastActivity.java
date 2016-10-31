package com.example.nvdovin.weatherapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.example.nvdovin.weatherapp.adapter.RecyclerViewAdapter;
import com.example.nvdovin.weatherapp.adapter.SeparatorDecoration;
import com.example.nvdovin.weatherapp.database.model.City;
import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;

import java.util.List;

public class ForecastActivity extends AppCompatActivity implements ForecastView {
    private RecyclerView recyclerView;
    static final int CELSIUS_SCALE = 273;
    float separatorHeight;

    TypedValue outValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

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

    }
}
