package com.example.nvdovin.weatherapp.utils;

import com.example.nvdovin.weatherapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherCodesMap {
    private Map<String, List<Integer>> map;
    private int iconResIndex;
    private int bckgResIndex;

    public WeatherCodesMap() {

        map = new HashMap<String, List<Integer>>();
        List<Integer> data = new ArrayList<>();

        data.add(R.string.weather_sunny);
        data.add(R.drawable.clear_sky_day);
        map.put("01d", data);

        data = new ArrayList<>();
        data.add(R.string.weather_cloudy);
        data.add(R.drawable.few_clouds_day);
        map.put("02d", data);

        data = new ArrayList<>();
        data.add(R.string.weather_cloudy);
        data.add(R.drawable.scattered_day);
        map.put("03d", data);

        data = new ArrayList<>();
        data.add(R.string.weather_cloudy);
        data.add(R.drawable.broken_clouds_day);
        map.put("04d", data);

        data = new ArrayList<>();
        data.add(R.string.weather_rainy);
        data.add(R.drawable.shower_rain_day);
        map.put("09d", data);

        data = new ArrayList<>();
        data.add(R.string.weather_rainy);
        data.add(R.drawable.rain_day);
        map.put("10d", data);

        data = new ArrayList<>();
        data.add(R.string.weather_thunder);
        data.add(R.drawable.thunderstorm_day);
        map.put("11d", data);

        data = new ArrayList<>();
        data.add(R.string.weather_snowy);
        data.add(R.drawable.snow_day);
        map.put("13d", data);

        data = new ArrayList<>();
        data.add(R.string.weather_foggy);
        data.add(R.drawable.mist_day);
        map.put("50d", data);

        data = new ArrayList<>();
        data.add(R.string.weather_clear_night);
        data.add(R.drawable.clear_sky_night);
        map.put("01n", data);

        data = new ArrayList<>();
        data.add(R.string.weather_cloudy);
        data.add(R.drawable.few_clouds_night);
        map.put("02n", data);

        data = new ArrayList<>();
        data.add(R.string.weather_cloudy);
        data.add(R.drawable.scattered_night);
        map.put("03n", data);

        data = new ArrayList<>();
        data.add(R.string.weather_cloudy);
        data.add(R.drawable.broken_clouds_night);
        map.put("04n", data);

        data = new ArrayList<>();
        data.add(R.string.weather_rainy);
        data.add(R.drawable.shower_rain_night);
        map.put("09n", data);

        data = new ArrayList<>();
        data.add(R.string.weather_rainy);
        data.add(R.drawable.rain_night);
        map.put("10n", data);

        data = new ArrayList<>();
        data.add(R.string.weather_thunder);
        data.add(R.drawable.thunderstorm_night);
        map.put("11n", data);

        data = new ArrayList<>();
        data.add(R.string.weather_snowy);
        data.add(R.drawable.snow_night);
        map.put("13n", data);

        data = new ArrayList<>();
        data.add(R.string.weather_foggy);
        data.add(R.drawable.mist_night);
        map.put("50n", data);

        iconResIndex = 0;// Icon is the first in list
        bckgResIndex = 1;// Background Res is second in list
    }

    public Integer getIconByID(String id) {
        return map.get(id).get(iconResIndex);
    }
    public Integer getBackgroundResById(String id){
        return map.get(id).get(bckgResIndex);
    }
}
