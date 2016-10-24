package com.example.nvdovin.weatherapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.factory.GreenDaoFactory;
import com.example.nvdovin.weatherapp.factory.RetrofitFactory;
import com.example.nvdovin.weatherapp.model.City;
import com.example.nvdovin.weatherapp.model.FirstModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<City> cities;
    private String query_city_name = "Chisinau";
    RetrofitFactory retrofitFactory;
    GreenDaoFactory greenDaoFactory;

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);


        txt = (TextView) findViewById(R.id.text);

        cities = new ArrayList<>();

        retrofitFactory = new RetrofitFactory();
        greenDaoFactory = new GreenDaoFactory(this);
        //TODO backgroundthread



        new AsyncTask<Void, Void, Void>(){


            @Override
            protected Void doInBackground(Void... params) {

                try {
                    Response<FirstModel> firstModelCall = retrofitFactory.getData(query_city_name).execute();
                    FirstModel model = firstModelCall.body();
                    greenDaoFactory.insert(model);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                cities = greenDaoFactory.loadCities();
                txt.setText(cities );
            }
        };







        /*GreenDaoFactory greenDaoFactory = new GreenDaoFactory();
        greenDaoFactory.insert(retrofitFactory.getRetrofitResponse());
*/


    }
}
