package com.example.nvdovin.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nvdovin.weatherapp.factory.RetrofitFactory;

public class MainActivity extends AppCompatActivity {
    private String query_city_name = "Chisinau";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitFactory retrofitFactory = new RetrofitFactory(query_city_name);

        /*GreenDaoFactory greenDaoFactory = new GreenDaoFactory();
        greenDaoFactory.insert(retrofitFactory.getRetrofitResponse());
*/


    }
}
