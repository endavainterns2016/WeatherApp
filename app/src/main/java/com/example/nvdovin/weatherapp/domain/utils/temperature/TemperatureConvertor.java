
package com.example.nvdovin.weatherapp.domain.utils.temperature;

public enum TemperatureConvertor implements TemperatureConvertorInterface {
    CELSIUS(0) {
        @Override
        public int convertToTemperature(int kelvinValue) {
            return kelvinValue - 273;
        }
    },
    FARENHEIT(1) {
        @Override
        public int convertToTemperature(int kelvinValue) {
            return (int) (kelvinValue * 5 / 9 - 459.67);
        }
    },
    KELVIN(2) {
        @Override
        public int convertToTemperature(int kelvinValue) {
            return kelvinValue;
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
