package com.example.nvdovin.weatherapp.domain.service;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.dao.CityDao;
import com.example.nvdovin.weatherapp.data.dao.DaoSession;
import com.example.nvdovin.weatherapp.data.model.City;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

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

    public City getCityById(Long cityId) {
        return daoSession.getCityDao().queryBuilder()
                .where(CityDao.Properties.Id.eq(cityId))
                .unique();
    }

    @SafeVarargs
    public final List<City> loadSortedCities(SortQueryBuilder<Property>... queryBuilders) {
        QueryBuilder<City> queryBuilder = daoSession.getCityDao().queryBuilder();
        for (SortQueryBuilder sortQueryBuilder : queryBuilders) {
            if (sortQueryBuilder.isAscending()) {
                queryBuilder = queryBuilder.orderAsc(sortQueryBuilder.getProperty());
            } else {
                queryBuilder = queryBuilder.orderDesc(sortQueryBuilder.getProperty());
            }
        }
        return queryBuilder.list();
    }

}
