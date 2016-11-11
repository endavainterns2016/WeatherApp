package com.example.nvdovin.weatherapp.presentation.history.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nvdovin.weatherapp.presentation.history.grid.GridHistoryFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class HistoryViewPagerAdapter extends FragmentPagerAdapter {

    private List<Long> timestampList;
    private Long cityId;

    public HistoryViewPagerAdapter(FragmentManager fm) {
        super(fm);
        timestampList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return GridHistoryFragment.newInstance(timestampList.get(position), cityId);
    }

    @Override
    public int getCount() {
        return timestampList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(timestampList.get(position) * 1000);
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void addDay(Long timestamp) {
        timestampList.add(timestamp);
    }

    public void addCity(Long cityId) {
        this.cityId = cityId;
    }

}
