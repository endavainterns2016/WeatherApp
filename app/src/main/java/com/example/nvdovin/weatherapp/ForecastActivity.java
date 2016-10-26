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
            builder.append(" ");
            builder.append(c.getLon());
            builder.append("\n");
            List<WeatherData> weatherDatas = c.getWeatherDataList();
            for (WeatherData w : weatherDatas) {
                builder.append(w.getHumidity());
                builder.append("\n");
                builder.append(w.getPressure());
                builder.append("\n");
                builder.append(w.getWeather());
                builder.append("\n");
                builder.append(w.getClouds());
                builder.append("\n");
                builder.append(w.getDt());
                builder.append("\n");
            }


            builder.append("------------------");
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
