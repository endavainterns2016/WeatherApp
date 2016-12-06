package com.example.nvdovin.weatherapp.domain.utils.executor;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.data.network.api.WeatherApi;
import com.example.nvdovin.weatherapp.data.network.response.GetCityListResponse;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.eventbus.EventBusWrapper;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.nvdovin.weatherapp.domain.utils.executor.Executor.APIARY_MOCK_CITIES_URL;
import static com.example.nvdovin.weatherapp.domain.utils.executor.Executor.API_KEY;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExecutorTest {

    @Mock
    private CityService cityService;
    @Mock
    private WeatherDataService weatherDataService;
    @Mock
    private DataMapper dataMapper;
    @Mock
    private SortQueryBuilder sortQueryBuilder;
    @Mock
    private WeatherApi weatherApi;
    @Mock
    private EventBusWrapper eventBusWrapper;


    private Executor executor;

    @Before
    public void setUp() {
        executor = new Executor(weatherApi, sortQueryBuilder, cityService, weatherDataService, dataMapper, eventBusWrapper);
    }

    @Test
    public void testExecuteWithList() throws Exception {
        Call<GetCityListResponse> mockCall = mock(Call.class);
        Call<City> cityMockCall = mock(Call.class);
        GetCityListResponse mockObject = mock(GetCityListResponse.class);
        City mockCity = mock(City.class);
        Response<City> cityResponse = Response.success(mockCity);
        Response<GetCityListResponse> successResponse = Response.success(mockObject);
        List<City> mockCities = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        strings.add("123");

        when(weatherApi.getCities(APIARY_MOCK_CITIES_URL)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(successResponse);
        when(mockObject.getCities()).thenReturn(strings);
        when(weatherApi.getWeatherData(anyString(), anyString())).thenReturn(cityMockCall);
        when(cityMockCall.execute()).thenReturn(cityResponse);
        when(cityService.loadSortedCities(sortQueryBuilder)).thenReturn(mockCities);

        executor.execute();

        verify(weatherApi).getWeatherData(anyString(), anyString());
        verify(cityService).loadSortedCities(sortQueryBuilder);
        verify(eventBusWrapper).post(mockCities);
    }

    @Test
    public void testExecuteWithEmptyList() throws Exception {
        Call<GetCityListResponse> mockCall = mock(Call.class);
        GetCityListResponse mockObject = mock(GetCityListResponse.class);
        Response<GetCityListResponse> successResponse = Response.success(mockObject);
        List<City> mockCities = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        when(weatherApi.getCities(APIARY_MOCK_CITIES_URL)).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(successResponse);
        when(mockObject.getCities()).thenReturn(strings);

        executor.execute();

        verify(weatherApi, never()).getWeatherData(anyString(), anyString());
        verify(cityService).loadSortedCities(sortQueryBuilder);
        verify(eventBusWrapper).post(mockCities);
    }

}