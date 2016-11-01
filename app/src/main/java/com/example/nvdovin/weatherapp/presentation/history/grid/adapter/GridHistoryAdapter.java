package com.example.nvdovin.weatherapp.presentation.history.grid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.presentation.history.grid.viewholder.WeatherHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GridHistoryAdapter extends BaseAdapter {

    @BindView(R.id.image_view)
    TextView imageView;
    @BindView(R.id.temp_view)
    TextView tempView;
    @BindView(R.id.humidity_view)
    TextView humidityView;
    @BindView(R.id.time_view)
    TextView timeView;

    private List<WeatherData> weatherDataList;
    private LayoutInflater inflater;
    private Context context;

    public GridHistoryAdapter(List<WeatherData> weatherDataList, Context context) {
        this.weatherDataList = weatherDataList;
        this.context = context;
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
        WeatherData weatherData = weatherDataList.get(position);
        WeatherHolder weatherHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item_layout, parent, false);
            weatherHolder = new WeatherHolder(convertView);
            convertView.setTag(weatherHolder);
        } else {
            weatherHolder = (WeatherHolder) convertView.getTag();
        }

        weatherHolder.setWeather(weatherData, context);
        return convertView;
    }

    public void swap(List<WeatherData> weatherDatas) {
        weatherDataList = new ArrayList<>(weatherDatas);
        notifyDataSetChanged();
    }

}
