package com.example.nvdovin.weatherapp.domain.utils.mapper;

import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataMapper {

    private static final long ONE_DAY_IN_MILLISEC = 86400L;

    private WeatherDataService weatherDataService;
    private CityService cityService;

    public DataMapper(WeatherDataService weatherDataService, CityService cityService) {
        this.weatherDataService = weatherDataService;
        this.cityService = cityService;
    }

    public List<WeatherData> updatedWeatherData(List<WeatherData> weatherDataListFromNetwork) {

        for (WeatherData weatherDataFromNetwork : weatherDataListFromNetwork) {
            WeatherData weatherFromDB = weatherDataService.getWeatherByUniqueId(weatherDataFromNetwork);
            if (weatherFromDB != null) {
                weatherDataFromNetwork.setId(weatherFromDB.getId());
            }
        }

        return weatherDataListFromNetwork;
    }

    public List<CityForecast> loadCityWeatherForNow(List<City> cities) {
        Long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        List<CityForecast> cityForecastList = new ArrayList<>();
        for (City city : cities) {
            CityForecast cityForecast = new CityForecast();
            cityForecast.setCityId(city.getId());
            cityForecast.setCityName(city.getName());
            WeatherData weatherDataForNow = weatherDataService.getUnique(cityForecast.getCityId(), currentTime);
            cityForecast.setCurrentCityWeather(weatherDataForNow);
            cityForecastList.add(cityForecast);
        }

        return cityForecastList;
    }

    private DailyForecast getDailyForecast(Long cityId, Long timestamp) {
        DailyForecast dailyForecast = new DailyForecast();
        List<WeatherData> weatherDataForDay = weatherDataService.getWeatherDataForDay(cityId, timestamp, ONE_DAY_IN_MILLISEC);

        dailyForecast.setWeatherDataList(weatherDataForDay);
        dailyForecast.setCityId(cityId);
        dailyForecast.setDayTempMax(getTempMax(weatherDataForDay));
        dailyForecast.setDayTempMin(getTempMin(weatherDataForDay));

        dailyForecast.setDate(TimeUtils.setLongToDate(timestamp));
        return dailyForecast;
    }

    public List<DailyForecast> getDailyForecastList(Long cityId, Long timestamp, int numberOfDays) {
        List<DailyForecast> dailyForecastList = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {

            dailyForecastList.add(getDailyForecast(cityId, timestamp + i * ONE_DAY_IN_MILLISEC));
        }
        return dailyForecastList;
    }

    public int getTempMax(List<WeatherData> weatherDataList) {
        Double tempMax = weatherDataList.get(0).getTempMax();
        for (WeatherData weatherData : weatherDataList) {
            if (weatherData.getTempMax() > tempMax) {
                tempMax = weatherData.getTempMax();
            }
        }
        return tempMax.intValue();
    }

    public int getTempMin(List<WeatherData> weatherDataList) {
        Double tempMin = weatherDataList.get(0).getTempMin();
        for (WeatherData weatherData : weatherDataList) {
            if (weatherData.getTempMax() < tempMin) {
                tempMin = weatherData.getTempMax();
            }
        }
        return tempMin.intValue();
    }

    public List<WeatherData> getWeatherDataListByDTs(Long[] timestampArray, Long cityId) {

        List<WeatherData> weatherDataList = new ArrayList<>();
        for (int i = 0; i < timestampArray.length; i++) {
            WeatherData weatherData = weatherDataService.getWeatherDataByDT(timestampArray[i], cityId);
            if (weatherData != null) {
                weatherDataList.add(weatherData);
            }
        }
        return weatherDataList;
    }


}
