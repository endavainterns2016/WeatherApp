package com.example.nvdovin.weatherapp.presentation.main.weather.forecast.adapter;

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
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.utils.mapper.WeatherCodesMap;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.temperature.TemperatureConvertor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<ForecastRecyclerViewAdapter.CustomViewHolder> {
    private static final int TRANSPARENCY_ALPHA = 160;
    private static final String FONTS_LOCATION = "fonts/weathericons-regular-webfont.ttf";
    private List<CityForecast> cityForecastList;
    private Context context;
    private Typeface weatherFont;
    private SharedPrefs sharedPrefs;


    public ForecastRecyclerViewAdapter(List<CityForecast> cityForecastList, Context context) {
        this.context = context;
        sharedPrefs= new SharedPrefs(context);
        this.cityForecastList = cityForecastList;
        weatherFont = Typeface.createFromAsset(this.context.getAssets(), FONTS_LOCATION);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.forecast_recycler_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final CityForecast cityForecast = cityForecastList.get(position);
        final WeatherData currentWeather = cityForecast.getCurrentCityWeather();
        String weatherIconId = currentWeather.getWeatherIcon();
        holder.cityName.setText(cityForecast.getCityName());
        holder.cityWeatherDescription.setText(currentWeather.getWeatherDescription());

        int kelvinTemperature = cityForecast.getCurrentCityWeather().getTemp().intValue();

        holder.cityTemperature.setText(String.valueOf(TemperatureConvertor.fromId(sharedPrefs.getTempScaleFromPrefs()).convertToTemperature(kelvinTemperature)));
        holder.cityID = cityForecast.getCityId();
        holder.itemView.setBackgroundResource(setWeatherIcon(weatherIconId, holder.weatherIcon));
        holder.itemView.getBackground().setAlpha(TRANSPARENCY_ALPHA);
    }


    @Override
    public int getItemCount() {
        return cityForecastList.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.forecast_recycler_city_name)TextView cityName;
        @BindView(R.id.forecast_recycler_city_temperature)TextView cityTemperature;
        @BindView(R.id.forecast_recycler_city_weather)TextView cityWeatherDescription;
        @BindView(R.id.forecast_recycler_weather_icon)TextView weatherIcon;
        Long cityID;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            weatherIcon.setTypeface(weatherFont);
        }
        @OnClick(R.id.recycler_row_layout_id)
        public void onRecyclerRowClick(View v) {
            //To be edited
            Toast.makeText(v.getContext(), "You have selected " + cityName.getText() + " with the id - " + cityID, Toast.LENGTH_SHORT).show();

        }
    }
    private int setWeatherIcon(String id, TextView iconTextView) {
        WeatherCodesMap weatherCodesMap = new WeatherCodesMap();
        int resID = weatherCodesMap.getBackgroundResById(id);
        String icon = context.getString(weatherCodesMap.getIconByID(id));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            iconTextView.setText(Html.fromHtml(icon, Html.FROM_HTML_MODE_LEGACY));
        else
            iconTextView.setText(Html.fromHtml(icon));
        return resID;
    }

    public void swap(List<CityForecast> citiesForecast) {
        cityForecastList = new ArrayList<>(citiesForecast);
        notifyDataSetChanged();
    }

}
