package com.example.nvdovin.weatherapp.domain.utils.navigator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Navigator {

    public static final String ARGS_KEY = "HISTORY_ARGS";
    private static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";
    private static final String CITY_ID_KEY = "CITY_ID_KEY";
    private Context context;
    private Bundle sentBundle;
    private Intent intent;

    public Navigator(Context context) {
        this.context = context;
        this.sentBundle = new Bundle();
    }

    public void navigate() {
        intent.putExtra(ARGS_KEY, sentBundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void toClass(Class target) {
        this.intent = new Intent(context, target);
    }

    public void addCityId(Long cityId) {
        sentBundle.putLong(CITY_ID_KEY, cityId);
    }

    public void addTimestamp(Long timestamp) {
        sentBundle.putLong(TIMESTAMP_KEY, timestamp);
    }


    public static class Builder {

        private Class target;
        private Long cityId;
        private Long timestamp;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setDestination(Class target) {
            this.target = target;
            return this;
        }

        public Builder setCityId(Long cityId) {
            this.cityId = cityId;
            return this;
        }

        public Builder setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        public void commit(){
            Navigator navigator = new Navigator(context);
            navigator.toClass(target);
            navigator.addTimestamp(timestamp);
            navigator.addCityId(cityId);
            navigator.navigate();
        }


    }

}
