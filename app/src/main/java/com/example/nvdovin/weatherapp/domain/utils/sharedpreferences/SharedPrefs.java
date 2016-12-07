package com.example.nvdovin.weatherapp.domain.utils.sharedpreferences;

import android.content.SharedPreferences;

import java.util.concurrent.TimeUnit;

public class SharedPrefs {
    public final static String LAST_UPDATE_TIME_KEY = "LAST_UPDATE_TIME_KEY";
    public final static String TEMPERATURE_SCALE = "TEMPERATURE_SCALE";
    public final static int TIME_SINCE_LAST_UPDATE = 24;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefs(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
    }

    public void setLastUpdateTime() {
        editor.putLong(LAST_UPDATE_TIME_KEY, System.currentTimeMillis());
        editor.apply();
    }

    public boolean lastUpdateExceededLimit() {
        return System.currentTimeMillis() - sharedPreferences.getLong(LAST_UPDATE_TIME_KEY, 0) >
                TimeUnit.HOURS.toMillis(TIME_SINCE_LAST_UPDATE);
    }

    public int getTempScaleFromPrefs() {
        return sharedPreferences.getInt(TEMPERATURE_SCALE, 0);
    }

    public void saveTempScaleinPrefs(int scaleCode) {
        editor.putInt(TEMPERATURE_SCALE, scaleCode);
        editor.apply();
    }
}