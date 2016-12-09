package com.example.nvdovin.weatherapp.domain.service;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.SortQueryBuilder;
import com.example.nvdovin.weatherapp.data.dao.CityDao;
import com.example.nvdovin.weatherapp.data.dao.DaoSession;
import com.example.nvdovin.weatherapp.data.model.City;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    DaoSession daoSession;
    @Mock
    QueryBuilder queryBuilder;
    @Mock
    City city;
    @Mock
    CityDao cityDao;
    @Mock
    WhereCondition whereCondition;
    @Mock
    SortQueryBuilder sortQueryBuilder;
    @Mock
    Property property;
    @Mock
    Context context;
    @Mock
    List list;
    private CityService cityService;

    @Before
    public void setUp() throws Exception {
        cityService = new CityService(daoSession, context);
    }

    @Test
    public void testInsert() throws Exception {

        when(daoSession.getCityDao()).thenReturn(cityDao);

        cityService.insert(city);

        verify(daoSession).getCityDao();
        verify(cityDao).insertOrReplace(city);
    }

    @Test
    public void testGetCityById() throws Exception {

        initBaseCondition();

        when(queryBuilder.where(
                any(WhereCondition.class),
                any(WhereCondition.class)
        )).thenReturn(queryBuilder);
        when(queryBuilder.unique()).thenReturn(city);

        cityService.getCityById(1L);

        verifyBaseCondition();

        verify(queryBuilder).where(any(WhereCondition.class));
        verify(queryBuilder).unique();
    }

    @Test
    public void testLoadSortedCitiesOrderAsc() throws Exception {

        initBaseCondition();

        when(sortQueryBuilder.isAscending()).thenReturn(false);
        when(queryBuilder.list()).thenReturn(list);
        when(sortQueryBuilder.getProperty()).thenReturn(property);
        when(queryBuilder.orderDesc(property)).thenReturn(queryBuilder);

        cityService.loadSortedCities(sortQueryBuilder);

        verifyBaseCondition();
    }

    @Test
    public void testLoadSortedCitiesOrderDesc() throws Exception {

        initBaseCondition();

        when(sortQueryBuilder.isAscending()).thenReturn(true);
        when(queryBuilder.list()).thenReturn(list);
        when(sortQueryBuilder.getProperty()).thenReturn(property);
        when(queryBuilder.orderAsc(property)).thenReturn(queryBuilder);

        cityService.loadSortedCities(sortQueryBuilder);

        verifyBaseCondition();

        verify(queryBuilder).orderAsc(property);
    }

    private void initBaseCondition() {

        when(daoSession.getCityDao()).thenReturn(cityDao);
        when(cityDao.queryBuilder()).thenReturn(queryBuilder);

    }

    private void verifyBaseCondition() {

        verify(daoSession).getCityDao();
        verify(cityDao).queryBuilder();

    }

}