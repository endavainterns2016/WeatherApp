package com.example.nvdovin.weatherapp.domain.service;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.dao.CityDao;
import com.example.nvdovin.weatherapp.data.dao.DaoSession;
import com.example.nvdovin.weatherapp.data.model.City;

import org.greenrobot.greendao.Property;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class CityService {

    private DaoSession daoSession;
    private Context context;

    public CityService(DaoSession daoSession, Context context) {
        this.daoSession = daoSession;
        this.context = context;
    }

    public void insert(City city) {
        daoSession.getCityDao().insertOrReplace(city);
    }

    public Observable<City> getCityByIdObservable(Long cityId) {
        return Observable.just(daoSession.getCityDao().queryBuilder()
                .where(CityDao.Properties.Id.eq(cityId))
                .unique());
    }

    public Observable<List<City>> getSortedCitiesListObservable(SortQueryBuilder<Property>... queryBuilders) {
        return Observable.just(daoSession.getCityDao().queryBuilder())
                .map(cityQueryBuilder -> {
                    for (SortQueryBuilder sortQueryBuilder : queryBuilders) {
                        if (sortQueryBuilder.isAscending()) {
                            cityQueryBuilder = cityQueryBuilder.orderAsc(sortQueryBuilder.getProperty());
                        } else {
                            cityQueryBuilder = cityQueryBuilder.orderDesc(sortQueryBuilder.getProperty());
                        }
                    }
                    return cityQueryBuilder.list();
                });
    }
}
