package com.example.nvdovin.weatherapp.domain.utils.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils.MILLISECONDS;
import static com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils.UTC_TIMEZONE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TimeUtilsTest {
    @Test
    public void testConvertDateToFormat() throws Exception {
        String actualFormat = "EEEE";
        String expectedValue = "Wednesday";
        Date testDate = convertStringToDate("12-07-2016", "MM-dd-yyyy");

        String result = TimeUtils.convertDateToFormat(testDate, actualFormat);

        assertEquals(expectedValue, result);
    }

    @Test
    public void testConvertLongToDate() throws Exception {
        long inputValue = 10L;
        long expectedValue = 10000L;

        Date result = TimeUtils.convertLongToDate(inputValue);

        assertEquals(expectedValue, result.getTime());
    }

    @Test
    public void testAddDaysToDate() throws Exception {
        int days = 3;
        Date testDate = convertStringToDate("12-07-2016", "MM-dd-yyyy");
        Date expected = convertStringToDate("12-10-2016", "MM-dd-yyyy");

        Date result = TimeUtils.addDaysToDate(testDate, days);

        assertEquals(expected, result);
    }

    @Test
    public void testIsSameDate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse("21/12/2015");
        Date secondDate = sdf.parse("21/12/2015");

        assertTrue(TimeUtils.isSameDate(firstDate, secondDate));
    }

    @Test
    public void testIsNotSameDate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date firstDate = sdf.parse("20/12/2015");
        Date secondDate = sdf.parse("21/12/2015");

        assertFalse(TimeUtils.isSameDate(firstDate, secondDate));
    }

    @Test
    public void testGetAllPeriodsForDay() throws Exception {
        Long someValue = 1481166000L;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC_TIMEZONE));
        calendar.setTimeInMillis(someValue * MILLISECONDS);
        Long[] expectedValue = new Long[]{
                1481155200L,
                1481166000L,
                1481176800L,
                1481187600L,
                1481198400L,
                1481209200L,
                1481220000L,
                1481230800L
        };

        assertArrayEquals(expectedValue, TimeUtils.getAllPeriodsForDay(someValue));

    }

    @Test
    public void testGetDateArrayByDate() throws Exception {
        Long timeValueInMillis = 1481108952051L;
        Calendar cal = Calendar.getInstance();
        int daysAgo = 5;
        int daysAhead = 0;
        cal.setTimeInMillis(timeValueInMillis);
        Long[] expectedValue = new Long[]{
                1480672800L,
                1480759200L,
                1480845600L,
                1480932000L,
                1481018400L,
                1481104800L
        };

        assertArrayEquals(expectedValue, TimeUtils.getDateArrayByDate(cal, daysAgo, daysAhead));


    }

    @Test
    public void testGetPeriodByHours() throws Exception {
        int hours = 14;
        int expectedValue = 12;
        assertEquals(expectedValue, TimeUtils.getPeriodByHours(hours));
    }

    @Test
    public void testTimestampToHours() throws Exception {

        long timeValueInMillis = 1481108952L;
        int[] expectedValue = new int[]{
                11,
                14
        };
        assertArrayEquals(expectedValue, TimeUtils.timestampToHours(timeValueInMillis));
    }


    private Date convertStringToDate(String dateString, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}