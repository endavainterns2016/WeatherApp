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

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.temperature.TemperatureConverter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils.FONTS_LOCATION;

public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastViewHolder> {
    private static final int TRANSPARENCY_ALPHA = 160;
    private final OnItemClickListener listener;
    private List<CityForecast> cityForecastList;
    private Context context;
    private SharedPrefs sharedPrefs;
    private ImageUtils imageUtils;

    public ForecastRecyclerViewAdapter(List<CityForecast> cityForecastList,
                                       OnItemClickListener listener,
                                       Context context,
                                       SharedPrefs sharedPrefs,
                                       ImageUtils imageUtils) {
        this.context = context;
        this.cityForecastList = cityForecastList;
        this.listener = listener;
        this.sharedPrefs = sharedPrefs;
        this.imageUtils = imageUtils;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.forecast_recycler_row, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
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
        void onItemClick(Long cityId);
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.forecast_recycler_city_name)
        TextView cityName;
        @BindView(R.id.forecast_recycler_city_temperature)
        TextView cityTemperature;
        @BindView(R.id.forecast_recycler_city_weather)
        TextView cityWeatherDescription;
        @BindView(R.id.forecast_recycler_weather_icon)
        TextView weatherIcon;

        ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Typeface weatherFont = Typeface.createFromAsset(context.getAssets(), FONTS_LOCATION);
            weatherIcon.setTypeface(weatherFont);
            itemView.setOnClickListener(v -> {
                listener.onItemClick(cityForecastList.get(ForecastViewHolder.this.getLayoutPosition()).getCityId());
            });

        }

        public void bind(final CityForecast cityForecast) {
            final WeatherData currentWeather = cityForecast.getCurrentCityWeather();
            String weatherIconId = currentWeather.getWeatherIcon();
            cityName.setText(cityForecast.getCityName());
            cityWeatherDescription.setText(currentWeather.getWeatherDescription());

            int kelvinTemperature = cityForecast.getCurrentCityWeather().getTemp().intValue();

            cityTemperature.setText(TemperatureConverter.fromId(sharedPrefs.getTempScaleFromPrefs()).convertToTemperature(kelvinTemperature));
            itemView.setBackgroundResource(imageUtils.getBackgroundResById(weatherIconId));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                weatherIcon.setText(Html.fromHtml(context.getString(imageUtils.getIconResById(weatherIconId)), Html.FROM_HTML_MODE_LEGACY));
            } else {
                weatherIcon.setText(Html.fromHtml(context.getString(imageUtils.getIconResById(weatherIconId))));
            }
            itemView.getBackground().setAlpha(TRANSPARENCY_ALPHA);
        }

    }


}
