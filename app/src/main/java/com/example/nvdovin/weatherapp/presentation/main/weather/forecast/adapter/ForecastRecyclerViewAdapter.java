package com.example.nvdovin.weatherapp.presentation.main.weather.forecast.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.temperature.TemperatureConvertor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<ForecastRecyclerViewAdapter.CustomViewHolder> {
    private static final int TRANSPARENCY_ALPHA = 160;
    private final OnItemClickListener listener;
    private ImageUtils imageUtils;
    private List<CityForecast> cityForecastList;
    private Context context;
    private SharedPrefs sharedPrefs;

    public ForecastRecyclerViewAdapter(List<CityForecast> cityForecastList, OnItemClickListener listener, Context context) {
        this.context = context;
        this.cityForecastList = cityForecastList;
        this.listener = listener;
        sharedPrefs = new SharedPrefs(context);
        imageUtils = new ImageUtils(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.forecast_recycler_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final CityForecast cityForecast = cityForecastList.get(position);
        holder.bind(cityForecast);
    }

    @Override
    public int getItemCount() {
        return cityForecastList.size();
    }

    public void swap(List<CityForecast> citiesForecast) {
        cityForecastList = new ArrayList<>(citiesForecast);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(CityForecast cityForecast);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.forecast_recycler_city_name)
        TextView cityName;
        @BindView(R.id.forecast_recycler_city_temperature)
        TextView cityTemperature;
        @BindView(R.id.forecast_recycler_city_weather)
        TextView cityWeatherDescription;
        @BindView(R.id.forecast_recycler_weather_icon)
        TextView weatherIcon;
        Long cityID;
        View itemView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
            imageUtils.setTypeface(weatherIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(cityForecastList.get(getLayoutPosition()));
                }
            });
        }

        public void bind(final CityForecast cityForecast) {
            final WeatherData currentWeather = cityForecast.getCurrentCityWeather();
            String weatherIconId = currentWeather.getWeatherIcon();
            cityName.setText(cityForecast.getCityName());
            cityWeatherDescription.setText(currentWeather.getWeatherDescription());

            int kelvinTemperature = cityForecast.getCurrentCityWeather().getTemp().intValue();

            cityTemperature.setText(String.valueOf(TemperatureConvertor.fromId(sharedPrefs.getTempScaleFromPrefs()).convertToTemperature(kelvinTemperature)));
            cityID = cityForecast.getCityId();
            itemView.setBackgroundResource(imageUtils.setWeatherIcon(weatherIconId, weatherIcon));
            itemView.getBackground().setAlpha(TRANSPARENCY_ALPHA);
        }

    }


}
