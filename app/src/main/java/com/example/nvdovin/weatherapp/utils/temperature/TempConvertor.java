package com.example.nvdovin.weatherapp.utils.temperature;

public enum TempConvertor implements ConvertorInterface {
    CELSIUS(1) {
        @Override
        public int convertToTemperature(int kelvinValue) {
            return kelvinValue - 273;
        }
    },
    FARENHEIT(2) {
        @Override
        public int convertToTemperature(int kelvinValue) {
            return (int) (kelvinValue * 5 / 9 - 459.67);
        }
    },
    KELVIN(-1) {
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
        ConvertorInterface temComvertor = null;
        for (TempConvertor temComvertor1 : TempConvertor.values()) {
            if (temComvertor1.convertorId == id) {
                temComvertor = temComvertor1;
                break;
            }
        }
        return temComvertor;
    }

    public int getConvertorId() {
        return convertorId;
    }
}

