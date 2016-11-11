package com.example.nvdovin.weatherapp.presentation.main.weather.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.presentation.main.weather.history.adapter.HistoryFragmentPagerAdapter;

public class HistoryFragment extends Fragment{

    ViewPager viewPager;
    PagerSlidingTabStrip tabsStrip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weather_history_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.setAdapter(new HistoryFragmentPagerAdapter(getChildFragmentManager()));
        tabsStrip.setViewPager(viewPager);

    }
}
