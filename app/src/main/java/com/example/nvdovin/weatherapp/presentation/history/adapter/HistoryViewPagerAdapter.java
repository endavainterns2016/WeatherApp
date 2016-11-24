package com.example.nvdovin.weatherapp.presentation.history.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.presentation.history.grid.GridHistoryFragment;
import com.example.nvdovin.weatherapp.presentation.history.model.CityDate;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class HistoryViewPagerAdapter extends FragmentPagerAdapter {

    public static final int ONE_DAY = 1;
    public static final int TWO_DAY = 2;
    private static final String SPACE = " ";
    private static final int MILLISECONDS = 1000;
    private static final String UTC_TIMEZONE = "UTC";
    private CityDate cityDate;
    private Context context;

    public HistoryViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return GridHistoryFragment.newInstance(cityDate.getTimestampList().get(position), cityDate.getCityId());
    }

    @Override
    public int getCount() {
        return cityDate.getTimestampList().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      List<Long>  listOfDaysOrderAsc = cityDate.getTimestampList();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC_TIMEZONE));
        calendar.setTimeInMillis(listOfDaysOrderAsc.get(position) * MILLISECONDS);
        String title;
        if (position == listOfDaysOrderAsc.size() - ONE_DAY) {
            title = context.getResources().getString(R.string.today);
        } else if (position == listOfDaysOrderAsc.size() - TWO_DAY) {
            title = context.getResources().getString(R.string.yesterday);
        } else {
            title = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + SPACE + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        }
        return title;
    }

    public void setCityDate(CityDate cityDate) {
        this.cityDate = cityDate;
    }
}
