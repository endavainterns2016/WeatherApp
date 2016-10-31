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
    private static final int TRANSPARENCY_ALPHA = 160;
    private static final String DAY_CONSTANT = "d";
    private static final int MARSHMALLOW_VERSION = 24;
    private static final String FONTS_LOCATION = "fonts/weathericons-regular-webfont.ttf";
    private List<City> cityList = new ArrayList<City>();
    private int tempScale;
    private Context context;
    private Typeface weatherFont;


    public RecyclerViewAdapter(List<City> cityList, int tempScale, Context context) {
        this.context = context;
        this.cityList = cityList;
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
        City city = cityList.get(position);
        String weatherIconId = city.getWeatherDataList().get(0).getWeatherIcon();
        holder.cityName.setText(city.getName());
        holder.cityWeatherDescription.setText(city.getWeatherDataList().get(0).getWeatherDescription());
        holder.cityTemperature.setText(String.valueOf(city.getWeatherDataList().get(0).getTemp().intValue() - tempScale));
        holder.cityID = city.getId();
        holder.itemView.setBackgroundResource(setWeatherIcon(weatherIconId, holder.weatherIcon));
        holder.itemView.getBackground().setAlpha(TRANSPARENCY_ALPHA);
    }


    @Override
    public int getItemCount() {
        return cityList.size();
    }

    private int setWeatherIcon(String id, TextView iconTextView) {
        int resID = 0;
        String icon = "";
        if (id.substring(id.length() - 1).equals(DAY_CONSTANT)) {
            switch (id.substring(0, id.length() - 1)) {

                case "01":
                    icon = context.getString(R.string.weather_sunny);
                    resID = R.drawable.clear_sky_day;
                    break;
                case "02":
                    icon = context.getString(R.string.weather_cloudy);
                    resID = R.drawable.few_clouds_day;
                    break;
                case "03":
                    icon = context.getString(R.string.weather_cloudy);
                    resID = R.drawable.scattered_day;
                    break;
                case "04":
                    icon = context.getString(R.string.weather_cloudy);
                    resID = R.drawable.broken_clouds_day;
                    break;
                case "09":
                    icon = context.getString(R.string.weather_rainy);
                    resID = R.drawable.shower_rain_day;
                    break;
                case "10":
                    icon = context.getString(R.string.weather_rainy);
                    resID = R.drawable.rain_day;
                    break;
                case "11":
                    icon = context.getString(R.string.weather_thunder);
                    resID = R.drawable.thunderstorm_day;
                    break;
                case "13":
                    icon = context.getString(R.string.weather_snowy);
                    resID = R.drawable.snow_day;
                    break;
                case "50":
                    icon = context.getString(R.string.weather_foggy);
                    resID = R.drawable.mist_day;
                    break;
                default:
                    icon = context.getString(R.string.weather_drizzle);
                    resID = R.drawable.broken_clouds_day;
            }
        } else {
            switch (id.substring(0, id.length() - 1)) {

                case "01":
                    icon = context.getString(R.string.weather_clear_night);
                    resID = R.drawable.clear_sky_night;
                    break;
                case "02":
                    icon = context.getString(R.string.weather_cloudy);
                    resID = R.drawable.few_clouds_night;
                    break;
                case "03":
                    icon = context.getString(R.string.weather_cloudy);
                    resID = R.drawable.scattered_night;
                    break;
                case "04":
                    icon = context.getString(R.string.weather_cloudy);
                    resID = R.drawable.broken_clouds_night;
                    break;
                case "09":
                    icon = context.getString(R.string.weather_rainy);
                    resID = R.drawable.shower_rain_night;
                    break;
                case "10":
                    icon = context.getString(R.string.weather_rainy);
                    resID = R.drawable.rain_night;
                    break;
                case "11":
                    icon = context.getString(R.string.weather_thunder);
                    resID = R.drawable.thunderstorm_night;
                    break;
                case "13":
                    icon = context.getString(R.string.weather_snowy);
                    resID = R.drawable.snow_night;
                    break;
                case "50":
                    icon = context.getString(R.string.weather_foggy);
                    resID = R.drawable.mist_night;
                    break;
                default:
                    icon = context.getString(R.string.weather_drizzle);
                    resID = R.drawable.broken_clouds_night;
            }
        }

        if (Build.VERSION.SDK_INT >= MARSHMALLOW_VERSION)
            iconTextView.setText(Html.fromHtml(icon, Html.FROM_HTML_MODE_LEGACY));
        else
            iconTextView.setText(Html.fromHtml(icon));
        return resID;
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

}
