package com.example.nvdovin.weatherapp.domain.service;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.dao.DaoSession;
import com.example.nvdovin.weatherapp.data.dao.WeatherDataDao;
import com.example.nvdovin.weatherapp.data.model.WeatherData;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataServiceTest {

    private final static Long MOCK_LONG_VALUE = 123L;
    @Mock
    Context context;
    @Mock
    DaoSession daoSession;
    //-----------------
    @Mock
    List<WeatherData> weatherDataList;
    @Mock
    WeatherDataDao weatherDataDao;
    @Mock
    QueryBuilder<WeatherData> queryBuilder;
    @Mock
    WeatherData weatherData;
    private WeatherDataService weatherDataService;

    @Before
    public void setUp() throws Exception {
        weatherDataService = new WeatherDataService(daoSession, context);

        mockMethods();
    }

    @Test
    public void insert() throws Exception {

        weatherDataService.insert(weatherDataList);

        verify(daoSession).getWeatherDataDao();
        verify(weatherDataDao).insertOrReplaceInTx(weatherDataList);
    }

    @Test
    public void getWeatherDataForDay() throws Exception {
        when(queryBuilder.where(any(WhereCondition.class), any(WhereCondition.class), any(WhereCondition.class)))
                .thenReturn(queryBuilder);
        when(queryBuilder.list()).thenReturn(weatherDataList);

        weatherDataService.getWeatherDataForDay(MOCK_LONG_VALUE, MOCK_LONG_VALUE, MOCK_LONG_VALUE);

        verifyDao();
        verify(queryBuilder).where(any(WhereCondition.class), any(WhereCondition.class), any(WhereCondition.class));
        verify(queryBuilder).list();
    }

    @Test
    public void getWeatherDataByDT() throws Exception {
        when(queryBuilder.where(any(WhereCondition.class), any(WhereCondition.class)))
                .thenReturn(queryBuilder);
        when(queryBuilder.unique()).thenReturn(weatherData);

        weatherDataService.getWeatherDataByDT(MOCK_LONG_VALUE, MOCK_LONG_VALUE);

        verifyDao();
        verify(queryBuilder).where(any(WhereCondition.class), any(WhereCondition.class));
        verify(queryBuilder).unique();

    }

    @Test
    public void getWeatherByUniqueId() throws Exception {
        when(queryBuilder.where(any(WhereCondition.class)))
                .thenReturn(queryBuilder);
        when(queryBuilder.unique()).thenReturn(weatherData);

        weatherDataService.getWeatherByUniqueId(weatherData);

        verifyDao();
        verify(queryBuilder).where(any(WhereCondition.class));
        verify(queryBuilder).unique();

    }

    @Test
    public void getUnique() throws Exception {
        when(queryBuilder.where(any(WhereCondition.class)))
                .thenReturn(queryBuilder);
        when(queryBuilder.orderAsc(any(Property.class))).thenReturn(queryBuilder);
        when(queryBuilder.limit(anyInt())).thenReturn(queryBuilder);
        when(queryBuilder.unique()).thenReturn(weatherData);

        weatherDataService.getUnique(MOCK_LONG_VALUE, MOCK_LONG_VALUE);

        verifyDao();
        verify(queryBuilder).unique();

    }

    private void mockMethods() {
        when(daoSession.getWeatherDataDao()).thenReturn(weatherDataDao);
        when(weatherDataDao.queryBuilder()).thenReturn(queryBuilder);
    }

    private void verifyDao() {
        verify(daoSession).getWeatherDataDao();
        verify(weatherDataDao).queryBuilder();
    }

}