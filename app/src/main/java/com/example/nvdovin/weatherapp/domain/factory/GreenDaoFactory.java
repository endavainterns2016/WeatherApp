package com.example.nvdovin.weatherapp.domain.factory;

import android.content.Context;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.dao.CityDao;
import com.example.nvdovin.weatherapp.data.dao.DaoMaster;
import com.example.nvdovin.weatherapp.data.dao.DaoSession;
import com.example.nvdovin.weatherapp.data.dao.WeatherDataDao;
import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.CityForecast;
import com.example.nvdovin.weatherapp.domain.utils.updater.WeatherDataUpdater;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GreenDaoFactory {

    private static final String DB_NAME = "WEATHER_DB";
    private static final Long DAY_IN_MILLISECONDS_INCLUSIVE = 86400L;
    private static final Long DAY_IN_MILLISECONDS_EXCLUSIVE = 75600L;

    private final Context context;

    private final DaoSession daoSession;

    public GreenDaoFactory(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        this.context = context;
        WeatherDataUpdater.setDaoSession(daoSession);
    }

    public void insert(City city) {
        daoSession.getWeatherDataDao().insertOrReplaceInTx(city.getRawWeatherList());
        daoSession.getCityDao().insertOrReplace(city);
    }

    @SafeVarargs
    public final List<CityForecast> loadSortedCities(SortQueryBuilder<Property>... queryBuilders) {
        QueryBuilder<City> qb = daoSession.getCityDao().queryBuilder();
        for (SortQueryBuilder sortQueryBuilder : queryBuilders) {
            if (sortQueryBuilder.isAscending()) {
                qb = qb.orderAsc(sortQueryBuilder.getProperty());
            } else {
                qb = qb.orderDesc(sortQueryBuilder.getProperty());
            }
        }
        return loadCityWeatherForNow(qb.list());
    }

    public String getCityById(Long cityId){
        return daoSession.getCityDao().queryBuilder()
                .where(CityDao.Properties.Id.eq(cityId))
                .unique()
                .getName();
    }

    public WeatherData getWeatherDataByDT(Long dt, Long cityId){
        return daoSession
                .getWeatherDataDao()
                .queryBuilder()
                .where(
                        WeatherDataDao.Properties.CityId.eq(cityId),
                        WeatherDataDao.Properties.Dt.eq(dt))
                .unique();
    }
    public List<WeatherData> getWeatherDataForDay(Long cityId, Long dt, Long period){
        return daoSession.getWeatherDataDao().queryBuilder()
                .where(WeatherDataDao.Properties.CityId.eq(cityId), WeatherDataDao.Properties.Dt.ge(dt),
                        WeatherDataDao.Properties.Dt.le(dt+period))
                .list();

    }

    public List<CityForecast> loadCityWeatherForNow(List<City> cities) {
        Long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

        List<CityForecast> cityForecastList = new ArrayList<>();
        for (City city : cities) {
            CityForecast cityForecast = new CityForecast();
            cityForecast.setCityId(city.getId());
            cityForecast.setCityName(city.getName());
            WeatherData weatherDataForNow = daoSession.getWeatherDataDao().queryBuilder().where(
                    new WhereCondition.StringCondition(context.getString(R.string.current_time_query),
                            String.valueOf(cityForecast.getCityId()), String.valueOf(currentTime)
                    )).unique();
            cityForecast.setCurrentCityWeather(weatherDataForNow);
            cityForecastList.add(cityForecast);
        }
        return cityForecastList;
    }

}
