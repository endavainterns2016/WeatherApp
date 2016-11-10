package com.example.nvdovin.weatherapp.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    private static final int ONE_DAY = 1;
    private static final int RESET_VALUE = 0;
    private static final int PERIOD = 3;

    public long[] getDateArrayByDate(Calendar date, int daysAgo, int daysAhead) {
        long dates[] = new long[daysAgo + ONE_DAY + daysAhead];
        date.set(Calendar.HOUR_OF_DAY, getPeriodByHours(date.get(Calendar.HOUR_OF_DAY)));
        date.set(Calendar.MINUTE, RESET_VALUE);
        date.set(Calendar.MILLISECOND, RESET_VALUE);
        date.set(Calendar.SECOND, RESET_VALUE);
        date.roll(Calendar.DAY_OF_YEAR, -daysAgo);
        for (int i = 0; i < dates.length; i++) {
            dates[i] = TimeUnit.MILLISECONDS.toSeconds(date.getTimeInMillis());
            date.roll(Calendar.DAY_OF_YEAR, ONE_DAY);
        }
        return dates;
    }

    public int getPeriodByHours(int hours) {
        return hours - (hours % PERIOD);
    }

}
