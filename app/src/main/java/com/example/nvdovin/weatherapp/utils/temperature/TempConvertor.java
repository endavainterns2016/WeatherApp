
package com.example.nvdovin.weatherapp.utils.temperature;

public enum TempConvertor implements ConvertorInterface {
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

    TempConvertor(int convertorId) {
        this.convertorId = convertorId;
    }

    public static ConvertorInterface fromId(int id) {
        for (TempConvertor temComvertor : TempConvertor.values()) {
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
