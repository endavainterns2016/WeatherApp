package com.example.nvdovin.weatherapp.domain.utils.temperature;

import android.text.SpannableString;

public interface Converter {
    String CELSIUS_SIGN = " °C";
    String FAHRENHEIT_SIGN = " °F";
    String KELVIN_SIGN = " °K";
    float PROPORTION = 2f;
    Integer CELSIUS_SCALE = 273;
    float FAHRENHEIT_COEFFICIENT = 9 / 5;
    float MINIM_FAHRENHEIT_VALUE = 459;
    SpannableString convertToTemperature(int kelvinValue);
}