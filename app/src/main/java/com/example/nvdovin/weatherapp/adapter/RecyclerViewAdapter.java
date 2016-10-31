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
import com.example.nvdovin.weatherapp.database.model.City;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
    private List<City> cityList = new ArrayList<City>();
    private int tempScale;
    Context context;
    private static final String ICON_URL = "http://openweathermap.org/img/w/";
    Typeface weatherFont;


    public RecyclerViewAdapter(List<City> cityList, int tempScale, Context context) {
        this.context = context;
        this.cityList = cityList;
        this.tempScale = tempScale;
        weatherFont = Typeface.createFromAsset(this.context.getAssets(), "fonts/weathericons-regular-webfont.ttf");


    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_recycler_row, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.cityName.setText(city.getName());
        holder.cityWeatherDescription.setText(city.getWeatherDataList().get(0).getWeatherDescription());
        holder.cityTemperature.setText(String.valueOf(city.getWeatherDataList().get(0).getTemp().intValue() - tempScale));
        holder.cityID = city.getId();
        setWeatherIcon(Integer.valueOf(city.getWeatherDataList().get(0).getWeatherIcon()), holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
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

    private void setWeatherIcon(int actualId, TextView iconTextView) {
        int id = actualId / 100;
        String icon = "";
        switch (id) {
            case 2:
                icon = "&#xf01e";
                break;
            case 3:
                icon = "&#xf01c";
                break;
            case 7:
                icon = "&#xf014";
                break;
            case 8:
                icon = "&#xf013";
                break;
            case 6:
                icon = "&#xf01b";
                break;
            case 5:
                icon = "&#xf019";
                break;

        }

        if (Build.VERSION.SDK_INT >= 24)
            iconTextView.setText(Html.fromHtml(icon, Html.FROM_HTML_MODE_LEGACY));
        else
            iconTextView.setText(Html.fromHtml(icon));

    }

}
