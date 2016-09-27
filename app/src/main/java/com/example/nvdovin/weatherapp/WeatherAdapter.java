package com.example.nvdovin.weatherapp;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.Model.Weather;

import java.util.List;

/**
 * Created by nvdovin on 9/27/2016.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<Weather> weatherList;
    Typeface weatherFont;


    public WeatherAdapter(Context context, List<Weather> weatherList){
        this.weatherList = weatherList;
        if(weatherFont == null){
            weatherFont = Typeface.createFromAsset(context.getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Weather weather = weatherList.get(position);
        holder.city.setText(weather.getCity());
        holder.description.setText(weather.getDescription());
        holder.temperature.setText(weather.getTemperature());
        holder.humidity.setText(weather.getHumidity());
        holder.pressure.setText(weather.getPressure());
        holder.updateOn.setText(weather.getUpdateOn());
        holder.icon.setText(Html.fromHtml(weather.getIcon()));
        holder.icon.setTypeface(weatherFont);

    }


    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView city, description, temperature, humidity, pressure, updateOn,icon, sunrise;


        public ViewHolder(View itemView) {
            super(itemView);
            city = (TextView) itemView.findViewById(R.id.city_field);
            description = (TextView) itemView.findViewById(R.id.details_field);
            temperature = (TextView) itemView.findViewById(R.id.current_temperature_field);
            humidity = (TextView) itemView.findViewById(R.id.humidity_field);
            pressure = (TextView) itemView.findViewById(R.id.pressure_field);
            updateOn = (TextView) itemView.findViewById(R.id.updated_field);
            icon = (TextView) itemView.findViewById(R.id.weather_icon);
        }
    }
}
