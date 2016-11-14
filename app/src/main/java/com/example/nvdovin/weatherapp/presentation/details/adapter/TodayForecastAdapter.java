package com.example.nvdovin.weatherapp.presentation.details.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.utils.date.DateConvertor;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.temperature.TemperatureConvertor;

import butterknife.BindView;
import butterknife.ButterKnife;

class TodayForecastAdapter extends RecyclerView.Adapter<TodayForecastAdapter.CustomViewHolder> {
    private static final String DATE_FORMAT = "EEE HH:mm";
    private DailyForecast dailyForecast;
    private ImageUtils imageUtils;
    private DateConvertor dateConvertor;
    private SharedPrefs sharedPrefs;

    TodayForecastAdapter(DailyForecast dailyForecast, Context context) {
        this.dailyForecast = dailyForecast;
        imageUtils = new ImageUtils(context);
        dateConvertor = new DateConvertor();
        sharedPrefs = new SharedPrefs(context);

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_today_forecast_recycler_row, parent, false);
        return new TodayForecastAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        WeatherData weatherData = dailyForecast.getWeatherDataList().get(position);

        imageUtils.setWeatherIcon(weatherData.getWeatherIcon(), holder.icon);

        holder.temperature.setText(
                TemperatureConvertor
                        .fromId(sharedPrefs.getTempScaleFromPrefs())
                        .convertToTemperature(weatherData.getTemp().intValue())
        );
        holder.time.setText(dateConvertor.convertLongToFormat(weatherData.getDt(), DATE_FORMAT));
    }

    @Override
    public int getItemCount() {
        return dailyForecast.getWeatherDataList().size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.detail_today_icon)
        TextView icon;
        @BindView(R.id.detail_today_temperature)
        TextView temperature;
        @BindView(R.id.detail_today_time)
        TextView time;
        CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imageUtils.setTypeface(icon);
        }
    }
}
