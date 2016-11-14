
package com.example.nvdovin.weatherapp.domain.utils.temperature;

import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;

public enum TemperatureConvertor implements TemperatureConvertorInterface {
    CELSIUS(0) {
        @Override
        public SpannableString convertToTemperature(int kelvinValue) {

            String temperature = String.valueOf(kelvinValue - 273);
            SpannableString temperatureWithSymbol=  new SpannableString(temperature.concat(TemperatureConvertorInterface.CELSIUS_SIGN));
            temperatureWithSymbol.setSpan(new RelativeSizeSpan(TemperatureConvertorInterface.PROPORTION), 0, temperature.length() , 0);
            return temperatureWithSymbol;
        }
    },
    FAHRENHEIT(1) {
        @Override
        public SpannableString convertToTemperature(int kelvinValue) {
            String temperature = String.valueOf(kelvinValue * 9 / 5 - 459);
            SpannableString temperatureWithSymbol=  new SpannableString(temperature.concat(TemperatureConvertorInterface.FAHRENHEIT_SIGN));
            temperatureWithSymbol.setSpan(new RelativeSizeSpan(TemperatureConvertorInterface.PROPORTION), 0,temperature.length(), 0);
            return temperatureWithSymbol;
        }
    },
    KELVIN(2) {
        @Override
        public SpannableString convertToTemperature(int kelvinValue) {
            SpannableString temperatureWithSymbol=  new SpannableString(String.valueOf(kelvinValue).concat(TemperatureConvertorInterface.KELVIN_SIGN));
            temperatureWithSymbol.setSpan(new RelativeSizeSpan(TemperatureConvertorInterface.PROPORTION), 0,String.valueOf(kelvinValue).length(), 0);
            return temperatureWithSymbol;
        }
    };

    private int convertorId;

    TemperatureConvertor(int convertorId) {
        this.convertorId = convertorId;
    }

    public static TemperatureConvertorInterface fromId(int id) {
        for (TemperatureConvertor temComvertor : TemperatureConvertor.values()) {
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
