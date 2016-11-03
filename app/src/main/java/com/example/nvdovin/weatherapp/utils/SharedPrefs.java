package com.example.nvdovin.weatherapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.concurrent.TimeUnit;

public class SharedPrefs {
    private final static String LAST_UPDATE_TIME_KEY = "LAST_UPDATE_TIME_KEY";
    private final static int TIME_SINCE_LAST_UPDATE = 24;
    private SharedPreferences sharedPref;

    public SharedPrefs(Context context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setLastUpdateTime() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(LAST_UPDATE_TIME_KEY, System.currentTimeMillis());
        editor.apply();
    }

    public boolean lastUpdateExceededLimit() {
        if ((sharedPref.getLong(LAST_UPDATE_TIME_KEY, 0) == 0 ||
                System.currentTimeMillis() - sharedPref.getLong(LAST_UPDATE_TIME_KEY, 0) > TimeUnit.HOURS.toMillis(TIME_SINCE_LAST_UPDATE))) {
            setLastUpdateTime();
            return true;
        } else {
            return false;
        }
    }
}