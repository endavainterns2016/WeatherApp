package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.utils.eventbus.EventBusWrapper;
import com.example.nvdovin.weatherapp.domain.utils.executor.DefaultThreadPoolExecutor;
import com.example.nvdovin.weatherapp.domain.utils.executor.Executor;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ForecastPresenterTest {

    @Mock
    Navigator.Builder builder;
    private ForecastPresenter forecastPresenter;
    @Mock
    private ForecastView view;
    @Mock
    private SharedPrefs sharedPrefs;
    @Mock
    private CityService cityService;
    @Mock
    private Executor executor;
    @Mock
    private DataMapper dataMapper;
    @Mock
    private DefaultThreadPoolExecutor defaultThreadPoolExecutor;
    @Mock
    private SortQueryBuilder sortQueryBuilder;
    @Mock
    private EventBusWrapper eventBusWrapper;

    @Before
    public void setUp() throws Exception {
        forecastPresenter = new ForecastPresenter(
                executor,
                cityService,
                view,
                sharedPrefs,
                dataMapper,
                defaultThreadPoolExecutor,
                sortQueryBuilder,
                eventBusWrapper,
                builder
        );

    }

    @Test
    public void testSetData() throws Exception {

        forecastPresenter.setData(ArgumentMatchers.<CityForecast>anyList());

        verify(view).displayData(ArgumentMatchers.<CityForecast>anyList());
        verify(view).hideLoading();
        verify(view).setRefreshing(false);
    }

    @Test
    public void testRefreshCalled() throws Exception {

        forecastPresenter.refreshCalled();

        verify(sharedPrefs).setLastUpdateTime();
        verify(defaultThreadPoolExecutor).executeBackground(executor);

    }

    @Test
    public void testWhenLastUpdateExceedLimit() throws Exception {

        doReturn(true).when(sharedPrefs).lastUpdateExceededLimit();

        forecastPresenter.checkLastUpdateTime();

        verify(sharedPrefs).lastUpdateExceededLimit();
        verify(defaultThreadPoolExecutor).executeBackground(executor);

    }


    @Test
    public void testWhenLastUpdateNotExceedLimit() throws Exception {

        List<City> mockCities = mock(List.class);

        doReturn(false).when(sharedPrefs).lastUpdateExceededLimit();

        when(cityService.loadSortedCities(sortQueryBuilder)).thenReturn(mockCities);

        forecastPresenter.checkLastUpdateTime();

        verify(cityService).loadSortedCities(sortQueryBuilder);
        verify(view).displayData(ArgumentMatchers.<CityForecast>anyList());
        verify(view).setRefreshing(false);
        verify(view).hideLoading();

    }

    @Test
    public void testNavigationButtonHandler(){

        when(builder.setCityId(anyLong())).thenReturn(builder);
        when(builder.setDestination(any(Class.class))).thenReturn(builder);
        when(builder.setTimestamp(anyLong())).thenReturn(builder);

        forecastPresenter.navigationButtonHandler(123L);

        verify(builder).setCityId(anyLong());
        verify(builder).setDestination(any(Class.class));
        verify(builder).setTimestamp(anyLong());
        verify(builder).commit();
    }

}