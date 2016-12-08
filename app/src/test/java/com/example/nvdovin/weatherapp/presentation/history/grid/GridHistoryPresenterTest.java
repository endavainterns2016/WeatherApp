package com.example.nvdovin.weatherapp.presentation.history.grid;

import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GridHistoryPresenterTest {

    @Mock
    DataMapper dataMapper;
    @Mock
    Navigator.Builder builder;
    @Mock
    GridHistoryView view;
    //----
    @Mock
    List<WeatherData> weatherDataList;
    private GridHistoryPresenter gridHistoryPresenter;

    @Before
    public void setUp() throws Exception {
        gridHistoryPresenter = new GridHistoryPresenter(dataMapper,
                view,
                builder);
    }

    @Test
    public void getWeatherData() throws Exception {
        when(dataMapper.getWeatherDataListByDTs(any(Long[].class), anyLong())).thenReturn(weatherDataList);
        setCityAndTimestamp();
        gridHistoryPresenter.getWeatherData();

        verify(view).displayHistory(ArgumentMatchers.<WeatherData>anyList());
    }

    @Test
    public void navigationButtonHandler() {
        when(builder.setCityId(anyLong())).thenReturn(builder);
        when(builder.setDestination(any(Class.class))).thenReturn(builder);
        when(builder.setTimestamp(anyLong())).thenReturn(builder);

        setCityAndTimestamp();
        gridHistoryPresenter.navigationButtonHandler();

        verify(builder).setCityId(anyLong());
        verify(builder).setDestination(any(Class.class));
        verify(builder).setTimestamp(anyLong());
        verify(builder).commit();
    }

    public void setCityAndTimestamp() {
        gridHistoryPresenter.setCityId(123L);
        gridHistoryPresenter.setTimestamp(123L);
    }

}