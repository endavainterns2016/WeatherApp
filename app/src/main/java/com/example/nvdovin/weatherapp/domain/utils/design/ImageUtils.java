package com.example.nvdovin.weatherapp.domain.utils.design;

import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.widget.TextView;

public class ImageUtils {

    private static final String FONTS_LOCATION = "fonts/weathericons-regular-webfont.ttf";

    public ImageUtils() {
    }

    public static int setWeatherIcon(String id, TextView iconTextView) {
        WeatherCodesMap weatherCodesMap = new WeatherCodesMap();
        int resID = weatherCodesMap.getBackgroundResById(id);
        String icon = iconTextView.getContext().getString(weatherCodesMap.getIconByID(id));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            iconTextView.setText(Html.fromHtml(icon, Html.FROM_HTML_MODE_LEGACY));
        } else {
            iconTextView.setText(Html.fromHtml(icon));
        }
        return resID;
    }

    public static void setTypeface(TextView textView) {
        Typeface weatherFont = Typeface.createFromAsset(textView.getContext().getAssets(), FONTS_LOCATION);
        textView.setTypeface(weatherFont);
    }
}
