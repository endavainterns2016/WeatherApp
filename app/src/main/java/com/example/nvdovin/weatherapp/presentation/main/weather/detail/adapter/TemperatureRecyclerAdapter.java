package com.example.nvdovin.weatherapp.fragments.cityDetails.adapter;


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
import com.example.nvdovin.weatherapp.domain.utils.mapper.WeatherCodesMap;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.temperature.TemperatureConvertor;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TemperatureRecyclerAdapter extends RecyclerView.Adapter<TemperatureRecyclerAdapter.CustomViewHolder> {

    private List<WeatherData> weatherDataList;
    private Context context;
    private Typeface weatherFont;
    private SharedPrefs sharedPrefs;
    private static final String FONTS_LOCATION = "fonts/weathericons-regular-webfont.ttf";

    public TemperatureRecyclerAdapter(List<WeatherData> weatherDataList, Context context) {
        this.weatherDataList = weatherDataList;
        weatherFont = Typeface.createFromAsset(context.getAssets(), FONTS_LOCATION);
        sharedPrefs = new SharedPrefs(context);
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city_details_recycler_temperature_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        WeatherData weatherData = weatherDataList.get(position);

        String temperature = String.valueOf(TemperatureConvertor
                .fromId(sharedPrefs.getTempScaleFromPrefs())
                .convertToTemperature(weatherData.getTemp().intValue()));
        holder.temperature.setText(temperature);
        String date = new SimpleDateFormat("EEE hh.mm").format(weatherData.getDt()*1000);
        holder.time.setText(String.valueOf(date));

        setWeatherIcon(weatherData.getWeatherIcon(), holder.icon);

    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.city_details_recycler_temperature_row_icon)TextView icon;
        @BindView(R.id.city_details_recycler_temperature_row_temperature)TextView temperature;
        @BindView(R.id.city_details_recycler_temperature_row_time)TextView time;
        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            icon.setTypeface(weatherFont);
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
}
