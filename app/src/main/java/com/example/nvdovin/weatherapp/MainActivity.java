package com.example.nvdovin.myopenweatherexample;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nvdovin.myopenweatherexample.Model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Weather> weatherArrayList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        weatherArrayList = new ArrayList<>();

      //  mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new WeatherAdapter(getApplicationContext(), weatherArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        asyncRequest();
    }

    private void asyncRequest(){

        new AsyncTask<String, Void, JSONObject>(){
            @Override
            protected JSONObject doInBackground(String... params) {
                JSONObject jsonWeather = null;
                try{
                //jsonWeather = ApiRequest.getWeatherJSON("819827","703448","2643743");
                jsonWeather = ApiRequest.getWeatherJSON("618426","824987","683506", "681290", "675810", "785842", "5095808", "727011");
                    //System.out.println(jsonWeather.toString());
                } catch (Exception e) {
                    Log.d("Error", "Cannot process JSON results", e);
                }
                    return jsonWeather;
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                try {

                    JSONArray array = jsonObject.getJSONArray("list");

                    if (jsonObject != null) {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject city = array.getJSONObject(i);
                            if(city != null){

                                Weather weather = new Weather();

                                JSONObject details = city.getJSONArray("weather").getJSONObject(0);
                                JSONObject main = city.getJSONObject("main");
                                DateFormat df = DateFormat.getDateTimeInstance();


                                String cityName = city.getString("name").toUpperCase(Locale.US) + ", " + city.getJSONObject("sys").getString("country");
                                String description = details.getString("description").toUpperCase(Locale.US);
                                String temperature = String.format("%.2f", main.getDouble("temp"))+ "°";
                                String humidity = main.getString("humidity") + "%";
                                String pressure = main.getString("pressure") + " hPa";
                                String updatedOn = df.format(new Date(city.getLong("dt")*1000));
                                String iconText = setWeatherIcon(details.getInt("id"),
                                        city.getJSONObject("sys").getLong("sunrise") * 1000,
                                        city.getJSONObject("sys").getLong("sunset") * 1000);
                                String sunrise = ""+ (city.getJSONObject("sys").getLong("sunrise") * 1000);
                                System.out.println("City " + i + " : " + cityName + " " + description + " " + temperature + " " + humidity
                                + " " + pressure + " " + " " + updatedOn);

                                weather.setCity(cityName);
                                weather.setDescription(description);
                                weather.setTemperature(temperature);
                                weather.setHumidity(humidity);
                                weather.setPressure(pressure);
                                weather.setUpdateOn(updatedOn);
                                weather.setIcon(iconText);
                                weather.setSunrise(sunrise);

                                weatherArrayList.add(weather);
                                mAdapter.notifyDataSetChanged();

                            }
                        }
                        System.out.println(weatherArrayList.size() + "");

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    public static String setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = "&#xf00d;";
            } else {
                icon = "&#xf02e;";
            }
        } else {
            switch(id) {
                case 2 : icon = "&#xf01e;";
                    break;
                case 3 : icon = "&#xf01c;";
                    break;
                case 7 : icon = "&#xf014;";
                    break;
                case 8 : icon = "&#xf013;";
                    break;
                case 6 : icon = "&#xf01b;";
                    break;
                case 5 : icon = "&#xf019;";
                    break;
            }
        }
        return icon;
    }

}
