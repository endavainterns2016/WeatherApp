package com.example.nvdovin.weatherapp.domain.utils.time;


import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class TimeUtilsTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testConvertDateToFormat() throws Exception {
        String expectedValue = "12-10-2016";
        assertEquals(TimeUtils.convertDateToFormat(new SimpleDateFormat("dd-MM-yyyy").parse(expectedValue), "dd-MM-yyyy"), expectedValue);
    }

    @Test
    public void testGetCurrentTime(){
        Long now = System.currentTimeMillis()/1000;
        assertEquals(TimeUtils.getCurrentTime(), now);
    }
}