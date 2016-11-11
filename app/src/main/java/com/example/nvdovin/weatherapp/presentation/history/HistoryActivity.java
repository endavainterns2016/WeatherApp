package com.example.nvdovin.weatherapp.presentation.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.presentation.history.adapter.HistoryViewPagerAdapter;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    public static final int DAYS_AHEAD = 0;
    public static final int DAYS_AGO = 5;

    @BindView(R.id.history_viewpager)
    ViewPager historyViewPager;
    @BindView(R.id.history_tabs)
    TabLayout historyTabLayout;

    TimeUtils timeUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ButterKnife.bind(this);

        timeUtils = new TimeUtils();

        setupViewPager(historyViewPager);
        historyTabLayout.setupWithViewPager(historyViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Long[] tmpDates = timeUtils.getDateArrayByDate(Calendar.getInstance(TimeZone.getTimeZone("UTC")), DAYS_AGO, DAYS_AHEAD);
        HistoryViewPagerAdapter historyViewPagerAdapter = new HistoryViewPagerAdapter(getSupportFragmentManager());
        historyViewPagerAdapter.addCity(618426L);//TODO implement cityId logic
        for (int i = 0; i < tmpDates.length; i++) {
            historyViewPagerAdapter.addDay(tmpDates[i]);
        }
        viewPager.setAdapter(historyViewPagerAdapter);
        viewPager.setCurrentItem(DAYS_AGO);
        viewPager.setOffscreenPageLimit(5);
    }


}
