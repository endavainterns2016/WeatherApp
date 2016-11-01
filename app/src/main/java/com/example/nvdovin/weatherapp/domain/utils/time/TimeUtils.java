package com.example.nvdovin.weatherapp.domain.utils.time;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    private static final int MILLISECONDS = 1000;
    private static final int ONE_DAY = 1;
    private static final int RESET_VALUE = 0;
    private static final int PERIOD = 3;
    private static final int COUNT_OF_RECORDINGS_FOR_DAY = 8;
    private static final String UTC_TIMEZONE = "UTC_TIMEZONE";

    public static Long[] getAllPeriodsForDay(Long timestamp) {
        Long dates[] = new Long[COUNT_OF_RECORDINGS_FOR_DAY];
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC_TIMEZONE));
        calendar.setTimeInMillis(timestamp * MILLISECONDS);
        calendar.set(Calendar.HOUR_OF_DAY, RESET_VALUE);
        calendar.set(Calendar.MINUTE, RESET_VALUE);
        calendar.set(Calendar.SECOND, RESET_VALUE);
        calendar.set(Calendar.MILLISECOND, RESET_VALUE);
        for (int i = 0; i < dates.length; i++) {
            dates[i] = TimeUnit.MILLISECONDS.toSeconds(calendar.getTimeInMillis());
            calendar.roll(Calendar.HOUR_OF_DAY, PERIOD);
        }

        return dates;
    }

    public static Long[] getDateArrayByDate(Calendar date, int daysAgo, int daysAhead) {
        Long dates[] = new Long[daysAgo + ONE_DAY + daysAhead];
        date.set(Calendar.HOUR_OF_DAY, getPeriodByHours(date.get(Calendar.HOUR_OF_DAY)));
        date.set(Calendar.MINUTE, RESET_VALUE);
        date.set(Calendar.SECOND, RESET_VALUE);
        date.set(Calendar.MILLISECOND, RESET_VALUE);
        date.roll(Calendar.DAY_OF_YEAR, -daysAgo);
        for (int i = 0; i < dates.length; i++) {
            dates[i] = TimeUnit.MILLISECONDS.toSeconds(date.getTimeInMillis());
            date.roll(Calendar.DAY_OF_YEAR, ONE_DAY);
        }
        return dates;
    }

    public static int getPeriodByHours(int hours) {
        return hours - (hours % PERIOD);
    }

    public static int[] timestampToHours(Long timestamp) {
        int[] period = new int[2];
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC_TIMEZONE));
        calendar.setTimeInMillis(timestamp * MILLISECONDS);
        period[0] = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.add(Calendar.HOUR_OF_DAY, PERIOD);
        period[1] = calendar.get(Calendar.HOUR_OF_DAY);
        return period;
    }

}
