package com.example.nvdovin.weatherapp.presentation.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.MainRecyclerForecastFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.forecast_progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        MainPresenter mainPresenter = new MainPresenter(this, this);
        mainPresenter.checkLastUpdateTime();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame_layout, new MainRecyclerForecastFragment())
                .commit();

    }
    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
