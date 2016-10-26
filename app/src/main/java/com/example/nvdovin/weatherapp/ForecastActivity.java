package com.example.nvdovin.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.model.City;
import com.example.nvdovin.weatherapp.model.WeatherData;

import java.util.List;

public class ForecastActivity extends AppCompatActivity implements ForecastView {
    RetrofitFactory retrofitFactory;
    GreenDaoFactory greenDaoFactory;

    ForecastPresenter forecastPresenter;

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt = (TextView) findViewById(R.id.text);

        retrofitFactory = new RetrofitFactory();
        greenDaoFactory = new GreenDaoFactory(this);

        forecastPresenter = new ForecastPresenter(retrofitFactory, greenDaoFactory, this);
        forecastPresenter.getData();

    }

    //check data
    @Override
    public void displayData(List<City> data) {
        StringBuilder builder = new StringBuilder();
        for(City c : data){
            builder.append(c.getName());
            builder.append("\n");
            builder.append(c.getLat());
            builder.append("\n");
            builder.append(c.getLat());
            builder.append(" ");
            builder.append(c.getLon());
            builder.append("\n");
            List<WeatherData> weatherDatas = c.getWeatherDataList();
            builder.append(weatherDatas.get(0).getHumidity());
            builder.append("\n");
            builder.append(weatherDatas.get(0).getPressure());
            builder.append("\n");
            builder.append(weatherDatas.get(0).getWeather());
            builder.append("\n");
            builder.append(weatherDatas.get(0).getClouds());
            builder.append("\n");
            builder.append(weatherDatas.get(0).getDt());
            builder.append("\n");

        }
        txt.setText(builder.toString());
    }

    @Override
    public void showLoading() {
        Toast.makeText(this,"Request started",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this,"Request stopped",Toast.LENGTH_SHORT).show();
    }
}
