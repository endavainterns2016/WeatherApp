package com.example.nvdovin.weatherapp.presentation.details.core;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.navigator.OperationNavigation;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailsPresenterTest {

    private final static Long MOCK_LONG_VALUE = (long) Math.random();
    @Mock
    CityService cityService;
    @Mock
    WeatherDataService weatherDataService;
    @Mock
    DataMapper dataMapper;
    @Mock
    DetailView detailView;
    @Mock
    Context context;
    //-------
    @Mock
    City city;
    @Mock
    List<DailyForecast> dailyForecastList;
    @Mock
    WeatherData weatherData;
    @Mock
    Navigator.Builder builder;
    @Mock
    SharedPrefs sharedPreferences;
    private DetailsPresenter detailsPresenter;

    @Before
    public void setUp() throws Exception {
        detailsPresenter = new DetailsPresenter(cityService,
                weatherDataService,
                dataMapper,
                detailView,
                builder,
                sharedPreferences);
    }

    @Test
    public void setupDetailView() throws Exception {
        mockMethods();

        detailsPresenter.setupDetailView(MOCK_LONG_VALUE, MOCK_LONG_VALUE);

        verify(detailView).setupView(anyString(), ArgumentMatchers.<DailyForecast>anyList(), any(WeatherData.class), eq(sharedPreferences));
    }

    @Test
    public void historyClickHandler() throws Exception {
        mockMethods();

        when(builder.setDestination(any(Class.class))).thenReturn(builder);
        when(builder.setCityId(anyLong())).thenReturn(builder);
        when(weatherData.getCityId()).thenReturn(MOCK_LONG_VALUE);

        detailsPresenter.setupDetailView(MOCK_LONG_VALUE, MOCK_LONG_VALUE);
        detailsPresenter.navigationButtonHandler();

        verify(builder).setDestination(any(Class.class));
        verify(builder).setCityId(anyLong());
        verify(builder).commit();

    }

    @Test
    public void testGetCallBack(){
        OperationNavigation generatedCallBack = detailsPresenter.getCallBack();
        assertNotNull(generatedCallBack);
    }

    public void mockMethods(){
        when(cityService.getCityById(anyLong())).thenReturn(city);
        when(city.getName()).thenReturn("Indianapolis");

        when(dataMapper.getDailyForecastList(anyLong(), anyLong(), anyInt())).thenReturn(dailyForecastList);
        when(weatherDataService.getUnique(anyLong(), anyLong())).thenReturn(weatherData);
    }

}