package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.data.network.api.WeatherApi;
import com.example.nvdovin.weatherapp.data.network.response.CityListResponse;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.service.WeatherDataService;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.rx.RxSchedulers;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.FuncN;

import static com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils.MILLISECONDS;

public class ForecastPresenter implements ViewPresenterNavigation {

    public static final String APIARY_MOCK_CITIES_URL = "https://private-f5a5b-citiesapi.apiary-mock.com/cities";
    public static final String API_KEY = "94e17e8755c5bc98caaf0a25e9c15d3f";
    private final RxSchedulers rxSchedulers;
    private ForecastView view;
    private SharedPrefs sharedPrefs;
    private CityService cityService;
    private WeatherDataService weatherDataService;
    private DataMapper dataMapper;
    private SortQueryBuilder sortQueryBuilder;
    private Navigator.Builder builder;
    private WeatherApi weatherApi;

    public ForecastPresenter(CityService cityService,
                             WeatherDataService weatherDataService,
                             ForecastView view,
                             SharedPrefs sharedPrefs,
                             DataMapper dataMapper,
                             SortQueryBuilder sortQueryBuilder,
                             Navigator.Builder builder,
                             WeatherApi weatherApi,
                             RxSchedulers rxSchedulers) {
        view.setOperationNavigation(this);
        this.cityService = cityService;
        this.weatherDataService = weatherDataService;
        this.view = view;
        this.sharedPrefs = sharedPrefs;
        this.dataMapper = dataMapper;
        this.sortQueryBuilder = sortQueryBuilder;
        this.builder = builder;
        this.weatherApi = weatherApi;
        this.rxSchedulers = rxSchedulers;
    }

    public void loadData(List<CityForecast> cityForecastList) {
        view.displayData(cityForecastList);
        view.hideLoading();
        view.setRefreshing(false);
    }


    public void refreshCalled() {
        sharedPrefs.setLastUpdateTime();
        getResponse();
    }

    private void sortData() {
        Observable<List<City>> cities = cityService.loadSortedCities(sortQueryBuilder);
        cities
                .subscribeOn(rxSchedulers.computation())
                .observeOn(rxSchedulers.androidUI())
                .subscribe(cityList -> {
                    loadData(dataMapper.loadCityWeatherForNow(cityList));
                });

    }

    public void checkLastUpdateTime() {
        if (sharedPrefs.lastUpdateExceededLimit()) {
            getResponse();
            sharedPrefs.setLastUpdateTime();
        } else {
            sortData();
        }
    }

    public void getResponse() {

        Observable<CityListResponse> cityListResponseObservable = weatherApi.getCities(APIARY_MOCK_CITIES_URL);
        cityListResponseObservable
                .subscribeOn(rxSchedulers.network())
                .flatMap(cityListResponse -> {
                    List<String> cities = cityListResponse.getCities();
                    List<Observable<City>> observables = new ArrayList<>();
                    for (String city : cities) {
                        observables.add(weatherApi.getWeatherData(API_KEY, city));
                    }
                    return Observable.zip(observables, new FuncN<Void>() {
                        @Override
                        public Void call(Object... args) {
                            for (Object o : args) {
                                City city = (City) o;
                                updateDatabase(city);
                            }

                            return null;
                        }
                    });
                })
                .observeOn(rxSchedulers.androidUI())
                .subscribe(aVoid -> {
                            sortData();
                        },
                        this::handleError);

    }

    private void updateDatabase(City city) {
        List<WeatherData> weatherDataList = dataMapper.updatedWeatherData(city.getWeatherDataList());
        weatherDataService.insert(weatherDataList);
        cityService.insert(city);
    }

    private void handleError(Throwable error) {
        view.showErrorDialog();
    }

    @Override
    public void navigationButtonHandler(Long cityId) {
        builder.setDestination(DetailActivity.class)
                .setCityId(cityId)
                .setTimestamp(System.currentTimeMillis() / MILLISECONDS)
                .commit();
    }
}
