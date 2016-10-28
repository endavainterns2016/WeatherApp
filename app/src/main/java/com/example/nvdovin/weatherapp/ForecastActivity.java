package com.example.nvdovin.weatherapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nvdovin.weatherapp.adapter.RecyclerViewAdapter;
import com.example.nvdovin.weatherapp.adapter.SeparatorDecoration;
import com.example.nvdovin.weatherapp.database.model.City;
import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;

import java.util.List;

public class ForecastActivity extends AppCompatActivity implements ForecastView {
    RetrofitFactory retrofitFactory;
    GreenDaoFactory greenDaoFactory;
    RecyclerView recyclerView;
    ForecastPresenter forecastPresenter;
    Integer tempScale = 273;
    TextView txt;

    private static String NEW_LINE = "\n";
    private static String SPACE = " ";
    private static String SIZE = "SIZE :";
    private static String MULTIDASH = "-------------------";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        txt = (TextView) findViewById(R.id.text);

        retrofitFactory = new RetrofitFactory();
        greenDaoFactory = new GreenDaoFactory(this);

        forecastPresenter = new ForecastPresenter(retrofitFactory, greenDaoFactory, this);
        forecastPresenter.getData();

    }

    @Override
    public void displayData(List<City> data) {
        SeparatorDecoration separatorDecoration = new SeparatorDecoration(this, Color.GRAY, 5.5f);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(separatorDecoration);
        recyclerView.setAdapter(new RecyclerViewAdapter(data, tempScale));
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Request started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "Request stopped", Toast.LENGTH_SHORT).show();
    }
}
