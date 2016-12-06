package com.example.nvdovin.weatherapp.domain.utils.mapper;

import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper.ONE_DAY_IN_MILLISEC;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataMapperTest {

    private static final Long MOCK_LONG_VALUE = 618426L;
    private static final int MOCK_INT_VALUE = 6;
    private static final Long[] MOCK_LONG_ARRAY = {1L, 2L, 3L};
    private static final Double MOCK_DOUBLE_VALUE = 42d;
    @Mock
    WeatherData weatherData;
    @Mock
    CityService cityService;
    @Mock
    WeatherDataService weatherDataService;
    private DataMapper dataMapper;
    private List<WeatherData> generatedWeatherDataList;

    @Before
    public void setUp() throws Exception {

        dataMapper = new DataMapper(weatherDataService, cityService);

        generatedWeatherDataList = generateRealWeatherDataList();

        when(weatherDataService.getWeatherDataForDay(anyLong(), anyLong(), eq(ONE_DAY_IN_MILLISEC)))
                .thenReturn(generatedWeatherDataList);
    }


    @Test
    public void updatedWeatherData() throws Exception {

        List<WeatherData> realWeatherDataList = generateRealWeatherDataList();

        when(weatherDataService.getWeatherByUniqueId(any(WeatherData.class))).thenReturn(weatherData);
        when(weatherData.getId()).thenReturn(MOCK_LONG_VALUE);

        List<WeatherData> expectedWeatherDataList = dataMapper.updatedWeatherData(realWeatherDataList);

        for (int i = 0; i < expectedWeatherDataList.size(); i++) {
            assertEquals(MOCK_LONG_VALUE, expectedWeatherDataList.get(i).getId());
        }

    }

    @Test
    public void loadCityWeatherForNow() throws Exception {

        List<City> realCityList = generateRealCityList();

        when(weatherDataService.getUnique(anyLong(), anyLong())).thenReturn(weatherData);

        List<CityForecast> generatedCityForecastList = dataMapper.loadCityWeatherForNow(realCityList);

        for (int i = 0; i < generatedCityForecastList.size(); i++) {
            assertEquals(realCityList.get(i).getId(), generatedCityForecastList.get(i).getCityId());
            assertEquals(realCityList.get(i).getName(), generatedCityForecastList.get(i).getCityName());
            assertEquals(weatherData, generatedCityForecastList.get(i).getCurrentCityWeather());
        }
    }

    @Test
    public void getDailyForecast() throws Exception {

        DailyForecast generatedDailyForecast = dataMapper.getDailyForecast(MOCK_LONG_VALUE, MOCK_LONG_VALUE);

        verify(weatherDataService).getWeatherDataForDay(anyLong(), anyLong(), anyLong());
        assertEquals(generatedWeatherDataList, generatedDailyForecast.getWeatherDataList());
        assertEquals(MOCK_LONG_VALUE, generatedDailyForecast.getCityId());
        assertEquals(Double.valueOf(MOCK_DOUBLE_VALUE + 1d), Double.valueOf(generatedDailyForecast.getDayTempMax()));
        assertEquals(Double.valueOf(MOCK_DOUBLE_VALUE - 11d), Double.valueOf(generatedDailyForecast.getDayTempMin()));
        assertEquals(TimeUtils.setLongToDate(MOCK_LONG_VALUE), generatedDailyForecast.getDate());
    }

    @Test
    public void getDailyForecastList() throws Exception {

        List<DailyForecast> generatedDailyForecastList = dataMapper.getDailyForecastList(MOCK_LONG_VALUE, MOCK_LONG_VALUE, MOCK_INT_VALUE);

        for (int i = 0; i < MOCK_INT_VALUE; i++) {
            assertEquals(generatedWeatherDataList, generatedDailyForecastList.get(i).getWeatherDataList());
            assertEquals(MOCK_LONG_VALUE, generatedDailyForecastList.get(i).getCityId());
            assertEquals(Double.valueOf(MOCK_DOUBLE_VALUE + 1d), Double.valueOf(generatedDailyForecastList.get(i).getDayTempMax()));
            assertEquals(Double.valueOf(MOCK_DOUBLE_VALUE - 11d), Double.valueOf(generatedDailyForecastList.get(i).getDayTempMin()));
            assertEquals(TimeUtils.setLongToDate(MOCK_LONG_VALUE + i * ONE_DAY_IN_MILLISEC), generatedDailyForecastList.get(i).getDate());
        }

    }

    @Test
    public void getTempMax() throws Exception {

        List<WeatherData> realWeatherDataList = generateRealWeatherDataList();

        int generatedTempMax = dataMapper.getTempMax(realWeatherDataList);

        assertEquals(Double.valueOf(MOCK_DOUBLE_VALUE + 1d), Double.valueOf(generatedTempMax));
    }

    @Test
    public void getTempMin() throws Exception {

        List<WeatherData> realWeatherDataList = generateRealWeatherDataList();

        int generatedTempMin = dataMapper.getTempMin(realWeatherDataList);

        assertEquals(Double.valueOf(MOCK_DOUBLE_VALUE - 11d), Double.valueOf(generatedTempMin));
    }

    @Test
    public void getWeatherDataListByDTs() throws Exception {

        when(weatherDataService.getWeatherDataByDT(anyLong(), anyLong())).thenReturn(weatherData);

        List<WeatherData> generatedWeatherDataList = dataMapper.getWeatherDataListByDTs(MOCK_LONG_ARRAY, MOCK_LONG_VALUE);

        for (int i = 0; i < MOCK_LONG_ARRAY.length; i++) {
            assertEquals(weatherData, generatedWeatherDataList.get(i));
        }
    }

    private List<WeatherData> generateRealWeatherDataList() {

        List<WeatherData> realWeatherDataList = new ArrayList<>();

        WeatherData realWeatherDataObj = new WeatherData();
        realWeatherDataObj.setTempMax(MOCK_DOUBLE_VALUE);
        realWeatherDataObj.setTempMin(MOCK_DOUBLE_VALUE - 10d);
        realWeatherDataList.add(realWeatherDataObj);

        realWeatherDataObj = new WeatherData();
        realWeatherDataObj.setTempMax(MOCK_DOUBLE_VALUE - 1d);
        realWeatherDataObj.setTempMin(MOCK_DOUBLE_VALUE - 11d);
        realWeatherDataList.add(realWeatherDataObj);

        realWeatherDataObj = new WeatherData();
        realWeatherDataObj.setTempMax(MOCK_DOUBLE_VALUE + 1d);
        realWeatherDataObj.setTempMin(MOCK_DOUBLE_VALUE - 9d);
        realWeatherDataList.add(realWeatherDataObj);

        return realWeatherDataList;
    }

    private List<City> generateRealCityList() {
        City realCityObj = new City();
        realCityObj.setName("Atlantis");
        realCityObj.setId(MOCK_LONG_VALUE);

        List<City> realCityList = new ArrayList<>();
        realCityList.add(realCityObj);
        realCityList.add(realCityObj);
        realCityList.add(realCityObj);

        return realCityList;
    }

}