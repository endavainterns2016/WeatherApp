package com.example.nvdovin.weatherapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.concurrent.TimeUnit;

public class SharedPrefs {
    private final static String LAST_UPDATE_TIME_KEY = "LAST_UPDATE_TIME";
    private final static int MINIMUM_UPDATE_INTERVAL = 24;
    private Context context;
    private SharedPreferences sharedPref;

    public SharedPrefs(Context context) {
        this.context = context;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setLastUpdateTime() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(LAST_UPDATE_TIME_KEY, System.currentTimeMillis());
        editor.commit();
    }

    public boolean lastUpdateExceededLimit() {
        if (sharedPref.getLong(LAST_UPDATE_TIME_KEY, 0) == 0 ||
                System.currentTimeMillis() - sharedPref.getLong(LAST_UPDATE_TIME_KEY, 0) > TimeUnit.HOURS.toMillis(MINIMUM_UPDATE_INTERVAL)) {
            setLastUpdateTime();
            return true;
        } else return false;
    }
}
