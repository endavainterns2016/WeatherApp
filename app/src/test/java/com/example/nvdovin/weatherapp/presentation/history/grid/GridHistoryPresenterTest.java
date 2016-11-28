package com.example.nvdovin.weatherapp.presentation.history.grid;

import android.content.Context;
import android.widget.AdapterView;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class)
public class GridHistoryPresenterTest {

    GridHistoryPresenter presenter;
    @Mock
    GridHistoryView gridHistoryView;
    @Mock
    CityService cityService;
    @Mock
    WeatherDataService weatherDataService;
    Context context = mock(Context.class, RETURNS_DEEP_STUBS);//RuntimeEnvironment.application.getApplicationContext();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new GridHistoryPresenter(cityService, weatherDataService, gridHistoryView);
    }

    @After
    public void tearDown() throws Exception {
        presenter = null;

    }

    @Test
    public void testGetWeatherData() {
        Long timestamp = 618426L;
        Long cityId = 618426L;
        List<WeatherData> mockedList = new ArrayList<>();
        when(weatherDataService.getWeatherDataListByDTs(any(Long[].class), eq(cityId))).thenReturn(mockedList);
        presenter.setTimestamp(timestamp);
        presenter.setCityId(cityId);
        presenter.getWeatherData();
        verify(weatherDataService, times(1)).getWeatherDataListByDTs(any(Long[].class), eq(cityId));
        verify(gridHistoryView, times(1)).displayHistory(eq(mockedList), any(AdapterView.OnItemClickListener.class));
    }
}