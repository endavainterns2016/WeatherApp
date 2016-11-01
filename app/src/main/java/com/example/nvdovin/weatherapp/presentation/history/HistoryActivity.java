package com.example.nvdovin.weatherapp.presentation.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;
import com.example.nvdovin.weatherapp.presentation.history.adapter.HistoryViewPagerAdapter;
import com.example.nvdovin.weatherapp.presentation.history.model.CityDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    public static final int DAYS_AHEAD = 0;
    public static final int DAYS_AGO = 5;
    public static final String UTC_TIMEZONE = "UTC";
    private static final String ARGS_KEY = "HISTORY_ARGS";
    private static final String CITY_ID_KEY = "CITY_ID_KEY";
    private static final int LAST_PAGE = 5;
    private static final int PAGE_LIMIT = 5;

    @BindView(R.id.history_viewpager)
    ViewPager historyViewPager;
    @BindView(R.id.history_tabs)
    TabLayout historyTabLayout;

    Long cityId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ButterKnife.bind(this);

        cityId = getIntent().getBundleExtra(ARGS_KEY).getLong(CITY_ID_KEY);

        setupViewPager(historyViewPager);
        historyTabLayout.setupWithViewPager(historyViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        CityDate cityDate = new CityDate();
        cityDate.setCityId(cityId);
        cityDate.setTimestampList(Arrays.asList(
                TimeUtils.getDateArrayByDate(
                        Calendar.getInstance(TimeZone.getTimeZone(UTC_TIMEZONE)), DAYS_AGO, DAYS_AHEAD
                )
        ));
        HistoryViewPagerAdapter historyViewPagerAdapter = new HistoryViewPagerAdapter(getSupportFragmentManager(), cityDate, this);
        viewPager.setAdapter(historyViewPagerAdapter);
        viewPager.setCurrentItem(LAST_PAGE);
        viewPager.setOffscreenPageLimit(PAGE_LIMIT);
    }


}
