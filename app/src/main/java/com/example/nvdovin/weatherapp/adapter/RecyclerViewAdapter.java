package com.example.nvdovin.weatherapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.model.ForecastNow;
import com.example.nvdovin.weatherapp.utils.WeatherCodesMap;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
    private static final int TRANSPARENCY_ALPHA = 160;
    private static final String DAY_CONSTANT = "d";
    private static final int MARSHMALLOW_VERSION = 24;
    private static final String FONTS_LOCATION = "fonts/weathericons-regular-webfont.ttf";
    private List<ForecastNow> forecastNowList;
    private int tempScale;
    private Context context;
    private Typeface weatherFont;


    public RecyclerViewAdapter(List<ForecastNow> forecastNowList, int tempScale, Context context) {
        this.context = context;
        this.forecastNowList = new ArrayList<>();
        this.forecastNowList = forecastNowList;
        this.tempScale = tempScale;
        weatherFont = Typeface.createFromAsset(this.context.getAssets(), FONTS_LOCATION);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.forecast_recycler_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ForecastNow forecastNow = forecastNowList.get(position);
        String weatherIconId = forecastNow.getCurrentCityWeather().getWeatherIcon();
        holder.cityName.setText(forecastNow.getCityName());
        holder.cityWeatherDescription.setText(forecastNow.getCurrentCityWeather().getWeatherDescription());
        holder.cityTemperature.setText(String.valueOf(forecastNow.getCurrentCityWeather().getTemp().intValue() - tempScale));
        holder.cityID = forecastNow.getCityId();
        holder.itemView.setBackgroundResource(setWeatherIcon(weatherIconId, holder.weatherIcon));
        holder.itemView.getBackground().setAlpha(TRANSPARENCY_ALPHA);
    }


    @Override
    public int getItemCount() {
        return forecastNowList.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView cityName, cityTemperature, cityWeatherDescription, weatherIcon;
        Long cityID;

        public CustomViewHolder(View itemView) {
            super(itemView);
            weatherIcon = (TextView) itemView.findViewById(R.id.forecast_recycler_weather_icon);
            weatherIcon.setTypeface(weatherFont);
            cityName = (TextView) itemView.findViewById(R.id.forecast_recycler_city_name);
            cityTemperature = (TextView) itemView.findViewById(R.id.forecast_recycler_city_temperature);
            cityWeatherDescription = (TextView) itemView.findViewById(R.id.forecast_recycler_city_weather);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //To be edited
                    Toast.makeText(v.getContext(), "You have selected " + cityName.getText() + " with the id - " + cityID, Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
    private int setWeatherIcon(String id, TextView iconTextView) {
        WeatherCodesMap weatherCodesMap = new WeatherCodesMap();
        int resID = weatherCodesMap.getBackgroundResById(id);
        String icon = context.getString(weatherCodesMap.getIconByID(id));

        if (Build.VERSION.SDK_INT >= MARSHMALLOW_VERSION)
            iconTextView.setText(Html.fromHtml(icon, Html.FROM_HTML_MODE_LEGACY));
        else
            iconTextView.setText(Html.fromHtml(icon));
        return resID;
    }

    public void swap(List<ForecastNow> forecastNowList) {

        if (this.forecastNowList != null) {
            this.forecastNowList.clear();
            this.forecastNowList.addAll(forecastNowList);
        } else {
            this.forecastNowList = forecastNowList;
        }
        notifyDataSetChanged();
    }

}
