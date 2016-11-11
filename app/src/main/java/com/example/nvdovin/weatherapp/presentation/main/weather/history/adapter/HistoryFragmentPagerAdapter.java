package com.example.nvdovin.weatherapp.presentation.main.weather.history.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nvdovin.weatherapp.presentation.history.grid.GridHistoryFragment;

public class HistoryFragmentPagerAdapter extends FragmentPagerAdapter {

    private Long cityId, timestamp;

    public HistoryFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public HistoryFragmentPagerAdapter(FragmentManager fm, Long cityId, Long timestamp) {
        this(fm);
        this.cityId = cityId;
        this.timestamp = timestamp;
    }


    @Override
    public Fragment getItem(int position) {
        GridHistoryFragment gridHistoryFragment;
        switch (position) {
            case 0:
                gridHistoryFragment = new GridHistoryFragment();
                gridHistoryFragment.setArguments(passData(cityId, timestamp));
                break;
            case 1:
                gridHistoryFragment = new GridHistoryFragment();
                gridHistoryFragment.setArguments(passData(cityId, timestamp));
                break;
            case 2:
                gridHistoryFragment = new GridHistoryFragment();
                gridHistoryFragment.setArguments(passData(cityId, timestamp));
                break;
            case 3:
                gridHistoryFragment = new GridHistoryFragment();
                gridHistoryFragment.setArguments(passData(cityId, timestamp));
                break;
            case 4:
                gridHistoryFragment = new GridHistoryFragment();
                gridHistoryFragment.setArguments(passData(cityId, timestamp));
                break;
            case 5:
                gridHistoryFragment = new GridHistoryFragment();
                gridHistoryFragment.setArguments(passData(cityId, timestamp));
                break;
            default:
                gridHistoryFragment = new GridHistoryFragment();
                break;
        }
        return gridHistoryFragment;
    }

    public Bundle passData(Long cityId, Long timestamp) {
        Bundle args = new Bundle();
        args.putLong("city_id", cityId);
        args.putLong("timestamp", timestamp);
        return args;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}
