package com.example.nvdovin.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.model.City;
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

    @Override
    public void displayData(List<City> data) {
        StringBuilder builder = new StringBuilder();
        for(City c : data){
            builder.append(c.getName());
            builder.append("\n");
            builder.append(c.getCoord().getLat());
            builder.append(" ");
            builder.append(c.getCoord().getLon());
            builder.append("\n");
            builder.append(c.getWeatherLists().get(0).getClouds().getAll());
            builder.append("\n");
            builder.append(c.getWeatherLists().get(0).getDtTxt());
            builder.append("\n");
            builder.append(c.getWeatherLists().get(0).getMain().getHumidity());
            builder.append("\n");
            builder.append(c.getWeatherLists().get(0).getWind().getDeg());
            builder.append("\n-------------------------------\n");
            //builder.append(c.getWeatherLists().get(0).getWeather().get(0).getDescription());
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
