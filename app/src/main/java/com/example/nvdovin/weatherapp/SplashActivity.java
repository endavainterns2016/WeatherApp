package com.example.nvdovin.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {
    public static final int DELAY_MILLIS = 2000;
    private Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(SplashActivity.this, ForecastActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, DELAY_MILLIS
        );

    }

}