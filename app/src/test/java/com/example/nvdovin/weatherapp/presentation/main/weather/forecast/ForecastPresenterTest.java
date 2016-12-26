package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import com.example.nvdovin.weatherapp.TestRxSchedulers;
import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.data.network.api.WeatherApi;
import com.example.nvdovin.weatherapp.data.network.response.CityListResponse;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.rx.RxSchedulers;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static com.example.nvdovin.weatherapp.presentation.main.weather.forecast.ForecastPresenter.APIARY_MOCK_CITIES_URL;
import static com.example.nvdovin.weatherapp.presentation.main.weather.forecast.ForecastPresenter.API_KEY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
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
    private WeatherDataService weatherDataService;
    @Mock
    private DataMapper dataMapper;
    @Mock
    private SortQueryBuilder sortQueryBuilder;
    @Mock
    private WeatherApi weatherApi;

    private RxSchedulers rxSchedulers = new TestRxSchedulers();

    @Before
    public void setUp() throws Exception {
        forecastPresenter = new ForecastPresenter(
                cityService,
                weatherDataService,
                view,
                sharedPrefs,
                dataMapper,
                sortQueryBuilder,
                builder,
                weatherApi,
                rxSchedulers
        );

    }

    @Test
    public void testGetResponseSuccess() {

        init();

        forecastPresenter.getResponse();

        check();
    }

    @Test
    public void testGetResponseError() {

        Throwable mockThrowable = mock(Throwable.class);

        when(weatherApi.getCities(APIARY_MOCK_CITIES_URL)).thenReturn(Observable.error(mockThrowable));

        forecastPresenter.getResponse();

        verify(view).showErrorDialog();

    }

    @Test
    public void testSetData() throws Exception {

        forecastPresenter.loadData(ArgumentMatchers.<CityForecast>anyList());

        verify(view).displayData(ArgumentMatchers.<CityForecast>anyList());
        verify(view).hideLoading();
        verify(view).setRefreshing(false);
    }

    @Test
    public void testRefreshCalled() throws Exception {

        init();

        forecastPresenter.refreshCalled();

        verify(sharedPrefs).setLastUpdateTime();

        check();


    }

    @Test
    public void testWhenLastUpdateExceedLimit() throws Exception {

        doReturn(true).when(sharedPrefs).lastUpdateExceededLimit();

        init();

        forecastPresenter.checkLastUpdateTime();

        check();

        verify(sharedPrefs).lastUpdateExceededLimit();

    }


    @Test
    public void testWhenLastUpdateNotExceedLimit() throws Exception {

        List<City> mockCities = mock(List.class);

        doReturn(false).when(sharedPrefs).lastUpdateExceededLimit();

        when(cityService.loadSortedCities(sortQueryBuilder)).thenReturn(Observable.just(mockCities));

        forecastPresenter.checkLastUpdateTime();

        verify(cityService).loadSortedCities(sortQueryBuilder);
        verify(view).displayData(ArgumentMatchers.<CityForecast>anyList());
        verify(view).setRefreshing(false);
        verify(view).hideLoading();

    }

    @Test
    public void testNavigationButtonHandler() {

        when(builder.setCityId(anyLong())).thenReturn(builder);
        when(builder.setDestination(any(Class.class))).thenReturn(builder);
        when(builder.setTimestamp(anyLong())).thenReturn(builder);

        forecastPresenter.navigationButtonHandler(123L);

        verify(builder).setCityId(anyLong());
        verify(builder).setDestination(any(Class.class));
        verify(builder).setTimestamp(anyLong());
        verify(builder).commit();
    }


    public void init() {

        doNothing().when(weatherDataService).insert(anyList());
        doNothing().when(cityService).insert(any(City.class));

        List<String> cities = new ArrayList<>();
        cities.add("Chisinau");

        City mockCity = mock(City.class);
        CityListResponse mockedCityListResponse = mock(CityListResponse.class);

        when(weatherApi.getCities(APIARY_MOCK_CITIES_URL)).thenReturn(Observable.just(mockedCityListResponse));
        when(mockedCityListResponse.getCities()).thenReturn(cities);
        when(weatherApi.getWeatherData(API_KEY, cities.get(0))).thenReturn(Observable.just(mockCity));
        when(cityService.loadSortedCities(sortQueryBuilder)).thenReturn(Observable.just(mock(List.class)));

    }

    public void check() {
        verify(dataMapper).updatedWeatherData(anyList());
        verify(weatherDataService).insert(anyList());
        verify(cityService).insert(any(City.class));
        verify(weatherApi).getCities(APIARY_MOCK_CITIES_URL);
    }

}