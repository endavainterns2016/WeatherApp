package com.example.nvdovin.weatherapp.domain.utils.sharedpreferences;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SharedPrefsTest {

    private final static String LAST_UPDATE_TIME_KEY = "LAST_UPDATE_TIME_KEY";
    private SharedPrefs testedSharedPrefs;
    @Mock
    SharedPreferences mockSharedPreferences;
    @Mock
    SharedPreferences.Editor editor;

    @Before
    public void setUp() throws Exception {
        testedSharedPrefs = new SharedPrefs(mockSharedPreferences);
    }


    @Test
    public void lastUpdateDidNotExceedLimit() throws Exception {

        when(mockSharedPreferences.getLong(LAST_UPDATE_TIME_KEY, 0))
                .thenReturn(System.currentTimeMillis());

        assertFalse(testedSharedPrefs.lastUpdateExceededLimit());
    }

    public void lastUpdateExceededLimit() throws Exception {
        when(mockSharedPreferences.getLong(LAST_UPDATE_TIME_KEY, 0))
                .thenReturn(0L);

        assertTrue(testedSharedPrefs.lastUpdateExceededLimit());
    }

    @Test
    public void getTempScaleFromPrefs() throws Exception {
        int testValue = (int) Math.random();

        when(mockSharedPreferences.getInt(anyString(), anyInt())).thenReturn(testValue);

        int generatedTempScale = testedSharedPrefs.getTempScaleFromPrefs();

        assertEquals(testValue, generatedTempScale);
    }

}