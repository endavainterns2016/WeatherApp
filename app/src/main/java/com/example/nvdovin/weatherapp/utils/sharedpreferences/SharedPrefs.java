package com.example.nvdovin.weatherapp.utils.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.concurrent.TimeUnit;

public class SharedPrefs {
    private final static String LAST_UPDATE_TIME_KEY = "LAST_UPDATE_TIME_KEY";
    private final static String TEMPERATURE_SCALE = "TEMPERATURE_SCALE";
    private final static int TIME_SINCE_LAST_UPDATE = 24;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public SharedPrefs(Context context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
    }

    public void setLastUpdateTime() {

        editor.putLong(LAST_UPDATE_TIME_KEY, System.currentTimeMillis());
        editor.apply();
    }

    public boolean lastUpdateExceededLimit() {
        return System.currentTimeMillis() - sharedPref.getLong(LAST_UPDATE_TIME_KEY, 0) >
                TimeUnit.HOURS.toMillis(TIME_SINCE_LAST_UPDATE);
    }

    public int getTempScaleFromPrefs() {
        return sharedPref.getInt(TEMPERATURE_SCALE, 0);
    }

    public void saveTempScaleinPrefs(int scaleCode) {
        editor.putInt(TEMPERATURE_SCALE, scaleCode);
        editor.apply();
    }
}