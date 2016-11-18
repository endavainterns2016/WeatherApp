package com.example.nvdovin.weatherapp.presentation.details.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;

import java.util.List;

import butterknife.BindView;

public class MainRecyclerAdapter extends RecyclerView.Adapter<ViewHolder<DailyForecast>> {

    private static final int FORECAST_CARD = 0;
    private static final int PARAMETERS_CARD = 1;
    private static final int NUMBER_OF_CARDS_TO_DISPLAY = 2;
    private List<DailyForecast> dailyForecastList;
    private WeatherData currentWeatherData;
    private Context context;


    public MainRecyclerAdapter(Context context, List<DailyForecast> dailyForecastList, WeatherData currentWeatherData) {
        this.dailyForecastList = dailyForecastList;
        this.currentWeatherData = currentWeatherData;
        this.context = context;
    }

    @Override
    public ViewHolder<DailyForecast> onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        switch (viewType) {
            case FORECAST_CARD:
                viewHolder = new ForecastCardHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.detail_forecast_card, parent, false));
                break;
            case PARAMETERS_CARD:
                viewHolder = new ParametersCardHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.detail_parameters_card, parent, false));
                break;
            default:
                viewHolder = new ForecastCardHolder(LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.detail_forecast_card, parent, false));
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder<DailyForecast> holder, int position) {
        holder.bindData(dailyForecastList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return NUMBER_OF_CARDS_TO_DISPLAY;
    }

    class ForecastCardHolder extends ViewHolder<DailyForecast> {
        @BindView(R.id.detail_forecast_today_recycler)
        RecyclerView todayDataRecycler;
        @BindView(R.id.detail_forecast_next_days_recycler)
        RecyclerView nextDaysRecycler;

        ForecastCardHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindData(DailyForecast dailyForecast) {
            Context context = itemView.getContext();
            todayDataRecycler.setLayoutManager(new LinearLayoutManager
                    (context, LinearLayoutManager.HORIZONTAL, false));
            todayDataRecycler.setAdapter(new TodayForecastAdapter(dailyForecast, context));

            nextDaysRecycler.setLayoutManager(new LinearLayoutManager(context));
            nextDaysRecycler.setNestedScrollingEnabled(false);
            nextDaysRecycler.setAdapter(new NextDaysRecyclerAdapter(dailyForecastList, context));
        }
    }

    class ParametersCardHolder extends ViewHolder<DailyForecast> {
        @BindView(R.id.detail_parameter_icon)
        TextView icon;
        @BindView(R.id.detail_parameter_clouds)
        TextView clouds;
        @BindView(R.id.detail_parameter_humidity)
        TextView humidity;
        @BindView(R.id.detail_parameter_pressure)
        TextView pressure;
        @BindView(R.id.detail_parameter_rain)
        TextView rain;
        @BindView(R.id.detail_parameter_windspeed)
        TextView windspeed;

        ParametersCardHolder(View itemView) {
            super(itemView);
            ImageUtils.setTypeface(icon);
        }

        @Override
        public void bindData(DailyForecast data) {
            ImageUtils.setWeatherIcon(currentWeatherData.getWeatherIcon(), icon);
            clouds.setText(String.valueOf(currentWeatherData.getClouds().intValue()).concat(context.getString(R.string.percent_sign)));
            humidity.setText(String.valueOf(currentWeatherData.getClouds().intValue()).concat(context.getString(R.string.percent_sign)));
            pressure.setText(String.valueOf(currentWeatherData.getPressure().intValue()).concat(context.getString(R.string.pressure_sign)));
            if (null != currentWeatherData.getRain()) {
                rain.setText(String.valueOf(currentWeatherData.getRain().intValue()).concat(context.getString( R.string.rain_sign)));
            } else {
                rain.setText(R.string.no_rain_message);
            }
            windspeed.setText(String.valueOf(currentWeatherData.getWindSpeed().intValue()).concat(context.getString(R.string.wind_speed_sign)));

        }
    }
}
