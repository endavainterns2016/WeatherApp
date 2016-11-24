package com.example.nvdovin.weatherapp.presentation.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.presentation.application.WeatherApplication;
import com.example.nvdovin.weatherapp.presentation.main.dagger.DaggerMainActivityComponent;
import com.example.nvdovin.weatherapp.presentation.main.dagger.MainActivityModule;
import com.example.nvdovin.weatherapp.presentation.main.weather.forecast.MainRecyclerForecastFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.forecast_progress_bar)
    ProgressBar progressBar;

    @Inject
    CityService cityService;

    @Inject
    WeatherDataService weatherDataService;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.builder()
                .appComponent(WeatherApplication.getAppComponent())
                .mainActivityModule(new MainActivityModule(this, this))
                .build()
                .inject(this);

        ButterKnife.bind(this);
        mainPresenter.checkLastUpdateTime();


        // This block is commented because i make changes only for MainActivity, so now for Fragment it doesn't work
        //See next PR
        /*getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame_layout, new MainRecyclerForecastFragment())
                .commit();*/

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
