package com.example.nvdovin.weatherapp.domain.service;

import android.content.Context;

import com.example.nvdovin.weatherapp.data.dao.CityDao;
import com.example.nvdovin.weatherapp.data.dao.DaoSession;
import com.example.nvdovin.weatherapp.data.model.City;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    private CityService cityService;

    private final static Long MOCK_LONG = 123L;

    @Mock
    Context context;
    @Mock
    DaoSession daoSession;
    //-------------------
    @Mock
    CityDao cityDao;
    @Mock
    QueryBuilder<City> cityQueryBuilder;
    @Mock
    WhereCondition whereCondition;
    @Mock
    City city;
    //---------------------------
    @Mock
    Observable<City> cityObservableMock;
    @Mock
    Subscription subscription;
    @Mock
    Action1 action1;

    @Before
    public void setUp() throws Exception {
        cityService = new CityService(daoSession, context);
    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void getCityByIdObservable() throws Exception {

        TestSubscriber<City> testSubscriber = new TestSubscriber<>();

        when(daoSession.getCityDao()).thenReturn(cityDao);
        when(cityDao.queryBuilder()).thenReturn(cityQueryBuilder);
        when(cityQueryBuilder.where(any(WhereCondition.class))).thenReturn(cityQueryBuilder);
        when(cityQueryBuilder.unique()).thenReturn(city);

        cityService.getCityByIdObservable(MOCK_LONG).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(city));
        testSubscriber.assertUnsubscribed();
    }

    @Test
    public void getSortedCitiesListObservable() throws Exception {

    }

}