package com.example.nvdovin.weatherapp.presentation.history.grid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;

import java.util.ArrayList;
import java.util.List;

public class GridHistoryAdapter extends BaseAdapter {

    private static final String HOUR_PRECISION = ":00";
    private static final String GAP = " - ";

    private List<WeatherData> weatherDataList;
    private LayoutInflater inflater;
    private ImageUtils imageUtils;
    private TimeUtils timeUtils;
    private TextView imageView;
    private TextView tempView;
    private TextView humidityView;
    private TextView timeView;

    public GridHistoryAdapter(List<WeatherData> weatherDataList, Context context) {
        this.weatherDataList = weatherDataList;
        imageUtils = new ImageUtils(context);
        timeUtils = new TimeUtils();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return weatherDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return weatherDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        WeatherData weatherData = weatherDataList.get(position);
        int[] period = timeUtils.timestampToHours(weatherData.getDt());
        if (convertView == null) {
            gridView = inflater.inflate(R.layout.grid_item_layout, parent, false);
            imageView = (TextView) gridView.findViewById(R.id.image_view);
            tempView = (TextView) gridView.findViewById(R.id.temp_view);
            humidityView = (TextView) gridView.findViewById(R.id.humidity_view);
            timeView = (TextView) gridView.findViewById(R.id.time_view);
        } else {
            gridView = convertView;
        }
        imageUtils.setTypeface(imageView);
        imageUtils.setWeatherIcon(weatherData.getWeatherIcon(), imageView);
        tempView.setText(String.valueOf(weatherData.getTemp()));
        humidityView.setText(String.valueOf(weatherData.getHumidity()));
        timeView.setText(period[0] + HOUR_PRECISION + GAP + period[1] + HOUR_PRECISION);
        return gridView;
    }

    public void swap(List<WeatherData> weatherDatas) {
        weatherDataList = new ArrayList<>(weatherDatas);
        notifyDataSetChanged();
    }

}
