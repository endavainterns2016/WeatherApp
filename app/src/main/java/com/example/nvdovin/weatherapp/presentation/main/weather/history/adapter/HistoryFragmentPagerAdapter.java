package com.example.nvdovin.weatherapp.presentation.main.weather.history.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nvdovin.weatherapp.presentation.main.weather.history.grid.GridHistoryFragment;

public class HistoryFragmentPagerAdapter extends FragmentPagerAdapter {

    public HistoryFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        GridHistoryFragment gridHistoryFragment;
        Bundle bundle;
        switch (position) {
            case 0:
                gridHistoryFragment = new GridHistoryFragment();
                bundle = new Bundle();
                bundle.putInt("int", 1);
                gridHistoryFragment.setArguments(bundle);
                break;
            case 1:
                gridHistoryFragment = new GridHistoryFragment();
                bundle = new Bundle();
                bundle.putInt("int", 2);
                gridHistoryFragment.setArguments(bundle);
                break;
            case 2:
                gridHistoryFragment = new GridHistoryFragment();
                bundle = new Bundle();
                bundle.putInt("int", 3);
                gridHistoryFragment.setArguments(bundle);
                break;
            case 3:
                gridHistoryFragment = new GridHistoryFragment();
                bundle = new Bundle();
                bundle.putInt("int", 4);
                gridHistoryFragment.setArguments(bundle);
                break;
            case 4:
                gridHistoryFragment = new GridHistoryFragment();
                bundle = new Bundle();
                bundle.putInt("int", 5);
                gridHistoryFragment.setArguments(bundle);
                break;
            case 5:
                gridHistoryFragment = new GridHistoryFragment();
                bundle = new Bundle();
                bundle.putInt("int", 6);
                gridHistoryFragment.setArguments(bundle);
                break;
            default:
                gridHistoryFragment = new GridHistoryFragment();
                break;
        }
        return gridHistoryFragment;
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
