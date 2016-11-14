package com.example.nvdovin.weatherapp.domain.utils.temperature;

import android.text.SpannableString;

public interface TemperatureConvertorInterface {
    static final String CELSIUS_SIGN = " °C";
    static final String FAHRENHEIT_SIGN = " °F";
    static final String KELVIN_SIGN = " °K";
    static final float PROPORTION = 2f;
    SpannableString convertToTemperature(int kelvinValue);
}