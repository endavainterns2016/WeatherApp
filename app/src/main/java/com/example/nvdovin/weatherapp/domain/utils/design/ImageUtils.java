package com.example.nvdovin.weatherapp.domain.utils.design;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.domain.utils.mapper.WeatherCodesMap;

public class ImageUtils {

    private final Context context;
    private static final String FONTS_LOCATION = "fonts/weathericons-regular-webfont.ttf";
    private Typeface weatherFont;

    public ImageUtils(Context context) {
        this.context = context;
        weatherFont = Typeface.createFromAsset(context.getAssets(), FONTS_LOCATION);
    }

    public int setWeatherIcon(String id, TextView iconTextView) {
        WeatherCodesMap weatherCodesMap = new WeatherCodesMap();
        int resID = weatherCodesMap.getBackgroundResById(id);
        String icon = context.getString(weatherCodesMap.getIconByID(id));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            iconTextView.setText(Html.fromHtml(icon, Html.FROM_HTML_MODE_LEGACY));
        else
            iconTextView.setText(Html.fromHtml(icon));
        return resID;
    }

    public void setTypeface(TextView textView){
        textView.setTypeface(weatherFont);
    }
}
