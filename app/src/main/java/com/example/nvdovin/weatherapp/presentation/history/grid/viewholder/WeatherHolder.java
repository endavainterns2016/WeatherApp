package com.example.nvdovin.weatherapp.presentation.history.grid.viewholder;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.temperature.TemperatureConverter;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils.FONTS_LOCATION;

public class WeatherHolder {

    private static final String HOUR_PRECISION = ":00";
    private static final String GAP = " - ";
    @BindView(R.id.image_view)
    TextView imageView;
    @BindView(R.id.temp_view)
    TextView tempView;
    @BindView(R.id.humidity_view)
    TextView humidityView;
    @BindView(R.id.time_view)
    TextView timeView;
    private ImageUtils imageUtils;

    public WeatherHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public void setWeather(WeatherData weatherData, Context context) {

        SharedPrefs sharedPrefs = new SharedPrefs(context);
        imageUtils = new ImageUtils();

        int[] period = TimeUtils.timestampToHours(weatherData.getDt());
        Typeface weatherFont = Typeface.createFromAsset(context.getAssets(), FONTS_LOCATION);
        imageView.setTypeface(weatherFont);
        // imageUtils.setWeatherIcon(weatherData.getWeatherIcon(), imageView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageView.setText(Html.fromHtml(context.getString(imageUtils.getIconResById(weatherData.getWeatherIcon())), Html.FROM_HTML_MODE_LEGACY));
        } else {
            imageView.setText(Html.fromHtml(context.getString(imageUtils.getIconResById(weatherData.getWeatherIcon()))));
        }
        String temp = String.valueOf(TemperatureConverter
                .fromId(sharedPrefs.getTempScaleFromPrefs())
                .convertToTemperature(weatherData.getTemp().intValue())) +
                context.getResources().getString(R.string.degrees_symbol);
        tempView.setText(temp);
        humidityView.setText(String.valueOf(weatherData.getHumidity()));
        timeView.setText(period[0] + HOUR_PRECISION + GAP + period[1] + HOUR_PRECISION);
    }
}
