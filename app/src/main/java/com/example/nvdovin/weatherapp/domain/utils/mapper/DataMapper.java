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

import rx.Observable;

public class DataMapper {

    public static final long ONE_DAY_IN_MILLISEC = 86400L;

    private WeatherDataService weatherDataService;
    private CityService cityService;

    public DataMapper(WeatherDataService weatherDataService, CityService cityService) {
        this.weatherDataService = weatherDataService;
        this.cityService = cityService;
    }

    public Observable<List<WeatherData>> getUpdatedWeatherListObservable(List<WeatherData> weatherDataListFromNetwork) {
        return Observable.just(weatherDataListFromNetwork)
                .map(weatherDataList -> {
                    for (WeatherData weatherDataFromNetwork : weatherDataListFromNetwork) {
                        weatherDataService.getWeatherDataByIdObservable(weatherDataFromNetwork).subscribe(weatherData -> {
                            if (weatherData != null) {
                                weatherDataFromNetwork.setId(weatherData.getId());
                            }
                        });
                    }
                    return weatherDataListFromNetwork;
                });
    }

    public Observable<List<CityForecast>> loadCityWeatherForNowObservable(List<City> cities){
        Long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        return Observable.just(new ArrayList<CityForecast>())
                .map(cityForecasts -> {
                    for (City city : cities) {
                        CityForecast cityForecast = new CityForecast();
                        cityForecast.setCityId(city.getId());
                        cityForecast.setCityName(city.getName());
                        weatherDataService.getUniqueWeatherDataObservable(cityForecast.getCityId(), currentTime)
                                .subscribe(cityForecast::setCurrentCityWeather);
                        cityForecasts.add(cityForecast);
                    }

                    return cityForecasts;
                });
    }


    private Observable<DailyForecast> getDailyForecastObservable(Long cityId, Long timestamp){
        return Observable.just(new DailyForecast())
                .map(dailyForecast -> {
                    weatherDataService.getWeatherDataForDayObservable(cityId, timestamp, ONE_DAY_IN_MILLISEC)
                            .subscribe(weatherDataList -> {
                                dailyForecast.setWeatherDataList(weatherDataList);
                                getTempMaxObservable(weatherDataList).subscribe(dailyForecast::setDayTempMax);
                                getTempMinObservable(weatherDataList).subscribe(dailyForecast::setDayTempMin);
                            });

                    dailyForecast.setCityId(cityId);
                    dailyForecast.setDate(TimeUtils.convertLongToDate(timestamp));

                    return dailyForecast;
                });
    }

    public Observable<List<DailyForecast>> getDailyForecastListObservable(Long cityId, Long timestamp, int numberOfDays) {
        return Observable.just(new ArrayList<DailyForecast>())
                .map(dailyForecasts -> {
                    for (int i = 0; i < numberOfDays; i++) {

                        getDailyForecastObservable(cityId, timestamp + i * ONE_DAY_IN_MILLISEC)
                                .subscribe(dailyForecasts::add);

                    }
                    return dailyForecasts;
                });
    }


    public Observable<Integer> getTempMinObservable(List<WeatherData> weatherDataList){
        return Observable.just(weatherDataList.get(0).getTempMin().intValue())
                .map(tempMin -> {
                    for (WeatherData weatherData : weatherDataList) {
                        if (weatherData.getTempMin() < tempMin) {
                            tempMin = weatherData.getTempMin().intValue();
                        }
                    }
                    return tempMin;
                });
    }

    public Observable<Integer> getTempMaxObservable(List<WeatherData> weatherDataList){
        return Observable.just(weatherDataList.get(0).getTempMax().intValue())
                .map(tempMax -> {
                    for (WeatherData weatherData : weatherDataList) {
                        if (weatherData.getTempMax() > tempMax) {
                            tempMax = weatherData.getTempMax().intValue();
                        }
                    }
                    return tempMax;
                });
    }

    public Observable<List<WeatherData>> getWeatherDataListByDTsObservable(Long[] timestampArray, Long cityId){
        return Observable.just(new ArrayList<WeatherData>())
                .map(weatherDatas -> {
                    for (int i = 0; i < timestampArray.length; i++) {

                        weatherDataService.getWeatherDataByTimeObservable(timestampArray[i], cityId)
                                .subscribe(weatherData -> {
                                    if (weatherData != null) {
                                        weatherDatas.add(weatherData);
                                    }
                                });
                    }
                    return weatherDatas;
                });
    }
}
