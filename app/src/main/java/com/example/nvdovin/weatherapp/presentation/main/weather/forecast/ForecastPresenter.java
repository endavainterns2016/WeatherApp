package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.domain.service.CityService;
import com.example.nvdovin.weatherapp.domain.utils.eventbus.EventBusWrapper;
import com.example.nvdovin.weatherapp.domain.utils.executor.DefaultThreadPoolExecutor;
import com.example.nvdovin.weatherapp.domain.utils.executor.Executor;
import com.example.nvdovin.weatherapp.domain.utils.navigator.Navigator;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.mapper.DataMapper;
import com.example.nvdovin.weatherapp.presentation.details.DetailActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import rx.Observable;

import static com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils.MILLISECONDS;

public class ForecastPresenter implements ViewPresenterNavigation {
    private ForecastView view;
    private SharedPrefs sharedPrefs;
    private CityService cityService;
    private Executor executor;
    private DataMapper dataMapper;
    private DefaultThreadPoolExecutor defaultThreadPoolExecutor;
    private SortQueryBuilder sortQueryBuilder;
    private Navigator.Builder builder;

    public ForecastPresenter(Executor executor,
                             CityService cityService,
                             ForecastView view,
                             SharedPrefs sharedPrefs,
                             DataMapper dataMapper,
                             DefaultThreadPoolExecutor defaultThreadPoolExecutor,
                             SortQueryBuilder sortQueryBuilder,
                             EventBusWrapper eventBusWrapper,
                             Navigator.Builder builder) {
        eventBusWrapper.register(this);
        view.setOperationNavigation(this);
        this.executor = executor;
        this.cityService = cityService;
        this.view = view;
        this.sharedPrefs = sharedPrefs;
        this.dataMapper = dataMapper;
        this.defaultThreadPoolExecutor = defaultThreadPoolExecutor;
        this.sortQueryBuilder = sortQueryBuilder;
        this.builder = builder;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setData(List<com.example.nvdovin.weatherapp.domain.model.CityForecast> cityForecastList) {
        view.displayData(cityForecastList);
        view.hideLoading();
        view.setRefreshing(false);
    }

    private void getData() {
        defaultThreadPoolExecutor.executeBackground(executor);
    }

    public void refreshCalled() {
        sharedPrefs.setLastUpdateTime();
        getData();
    }

    private void sortData() {
        List<City> cities = cityService.loadSortedCities(sortQueryBuilder);
        setData(dataMapper.loadCityWeatherForNow(cities));
    }

    public void checkLastUpdateTime() {
        if (sharedPrefs.lastUpdateExceededLimit()) {
            getData();
            sharedPrefs.setLastUpdateTime();
        } else {
            sortData();
        }
    }


    @Override
    public void navigationButtonHandler(Long cityId) {
        builder.setDestination(DetailActivity.class)
                .setCityId(cityId)
                .setTimestamp(System.currentTimeMillis() / MILLISECONDS)
                .commit();
    }

    @Override
    public void passClickHandlerObservable(Observable<Long> clickObservable) {
        clickObservable.subscribe(this::navigationButtonHandler);
    }
}
