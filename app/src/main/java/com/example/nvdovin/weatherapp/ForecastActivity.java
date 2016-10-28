package com.example.nvdovin.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nvdovin.weatherapp.database.model.City;
import com.example.nvdovin.weatherapp.database.model.WeatherData;
import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

public class ForecastActivity extends AppCompatActivity implements ForecastView {
    RetrofitFactory retrofitFactory;
    GreenDaoFactory greenDaoFactory;

    ForecastPresenter forecastPresenter;

    TextView txt;
    private List<String> cityList;

    private static String NEW_LINE = "\n";
    private static String SPACE = " ";
    private static String SIZE = "SIZE :";
    private static String MULTIDASH = "-------------------";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = new ArrayList<>();
        cityList.add("Chisinau");
        cityList.add("Madrid");

        txt = (TextView) findViewById(R.id.text);

        retrofitFactory = new RetrofitFactory();
        greenDaoFactory = new GreenDaoFactory(this);

        forecastPresenter = new ForecastPresenter(retrofitFactory, greenDaoFactory, this);
        forecastPresenter.getData(cityList);

    }

    @Override
    public void displayData(List<City> data) {
        StringBuilder builder = new StringBuilder();
        int weatherDataSize = 0;
        for (City c : data) {
            weatherDataSize += c.getWeatherDataList().size();
        }
        builder.append(SIZE + weatherDataSize + "\n");
        for(City c : data){
            builder.append(c.getName());
            builder.append(NEW_LINE);
            builder.append(c.getLat());
            builder.append(SPACE);
            builder.append(c.getLon());
            builder.append(NEW_LINE);
            List<WeatherData> weatherDatas = c.getWeatherDataList();
            for (WeatherData w : weatherDatas) {
                builder.append(w.getId());
                builder.append(NEW_LINE);
                builder.append(w.getHumidity());
                builder.append(NEW_LINE);
                builder.append(w.getPressure());
                builder.append(NEW_LINE);
                builder.append(w.getWeather());
                builder.append(NEW_LINE);
                builder.append(w.getClouds());
                builder.append(NEW_LINE);
                builder.append(w.getDt());
                builder.append(NEW_LINE);
            }


            builder.append(MULTIDASH);
            builder.append(NEW_LINE);
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
