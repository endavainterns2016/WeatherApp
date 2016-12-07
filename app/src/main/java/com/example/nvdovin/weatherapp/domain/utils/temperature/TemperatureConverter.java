
package com.example.nvdovin.weatherapp.domain.utils.temperature;

import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;

public enum TemperatureConverter implements Converter {
    CELSIUS(0) {
        @Override
        public SpannableString convertToTemperature(int kelvinValue) {

            String temperature = String.valueOf(kelvinValue - CELSIUS_SCALE);
            SpannableString temperatureWithSymbol = new SpannableString(temperature.concat(Converter.CELSIUS_SIGN));
            temperatureWithSymbol.setSpan(new RelativeSizeSpan(Converter.PROPORTION), 0, temperature.length() , 0);
            return temperatureWithSymbol;
        }
    },
    FAHRENHEIT(1) {
        @Override
        public SpannableString convertToTemperature(int kelvinValue) {
            String temperature = String.valueOf(kelvinValue * FAHRENHEIT_COEFFICIENT - MINIM_FAHRENHEIT_VALUE);
            SpannableString temperatureWithSymbol = new SpannableString(temperature.concat(Converter.FAHRENHEIT_SIGN));
            temperatureWithSymbol.setSpan(new RelativeSizeSpan(Converter.PROPORTION), 0,temperature.length(), 0);
            return temperatureWithSymbol;
        }
    },
    KELVIN(2) {
        @Override
        public SpannableString convertToTemperature(int kelvinValue) {
            SpannableString temperatureWithSymbol = new SpannableString(String.valueOf(kelvinValue).concat(Converter.KELVIN_SIGN));
            temperatureWithSymbol.setSpan(new RelativeSizeSpan(Converter.PROPORTION), 0,String.valueOf(kelvinValue).length(), 0);
            return temperatureWithSymbol;
        }
    };

    private int convertorId;

    TemperatureConverter(int convertorId) {
        this.convertorId = convertorId;
    }

    public static Converter fromId(int id) {
        for (TemperatureConverter temComvertor : TemperatureConverter.values()) {
            if (temComvertor.convertorId == id) {
                return temComvertor;
            }
        }
        return null;
    }

    public int getConvertorId() {
        return convertorId;
    }
}
