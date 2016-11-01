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
        Bundle bundle = new Bundle();


        //To Be Edited. Is put here to test the details fragment
/*        bundle.putLong("CITY_ID_KEY", 618426L);
        bundle.putLong("TIMESTAMP_KEY", 1478660400L);
        DetailsFragment cityDetailsFragment = new DetailsFragment();
        cityDetailsFragment.setArguments(bundle);*/
        getFragmentManager().beginTransaction()
                .replace(R.id.main_frame_layout, new MainRecyclerForecastFragment())
                .commit();
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

}
