package com.example.nvdovin.weatherapp.presentation.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.ForecastFragment;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame_layout, ForecastFragment.newInstance())
                .commit();

    }

}
