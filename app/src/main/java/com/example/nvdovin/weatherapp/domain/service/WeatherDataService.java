package com.example.nvdovin.weatherapp.domain.service;


import android.content.Context;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.dao.DaoSession;
import com.example.nvdovin.weatherapp.data.dao.WeatherDataDao;
import com.example.nvdovin.weatherapp.data.model.WeatherData;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class WeatherDataService {

    private DaoSession daoSession;
    private Context context;

    public WeatherDataService(DaoSession daoSession, Context context) {
        this.daoSession = daoSession;
        this.context = context;
    }

    public Subscriber insertInDbSubscriber(){
        return new Subscriber<List<WeatherData>>() {
            @Override
            public void onCompleted() {
                this.unsubscribe();
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("An error has happened - " + e);
            }

            @Override
            public void onNext(List<WeatherData> weatherDataList) {
                daoSession.getWeatherDataDao().insertOrReplaceInTx(weatherDataList);
            }
        };
    }


    public Observable<WeatherData> getUniqueWeatherDataObservable(Long cityId, long time){
        return Observable.just(daoSession
                .getWeatherDataDao()
                .queryBuilder()
                .where(
                        new WhereCondition.StringCondition(context.getString(R.string.time_query),
                                String.valueOf(cityId), String.valueOf(time)
                        ))
                .orderAsc(WeatherDataDao.Properties.Dt)
                .limit(1)
                .unique());
    }

    public Observable<WeatherData> getWeatherDataByIdObservable(WeatherData weatherDataFromNetwork){
        return Observable.just(daoSession
                .getWeatherDataDao()
                .queryBuilder()
                .where(WeatherDataDao.Properties.UniqueId.eq(weatherDataFromNetwork.getUniqueId()))
                .unique());
    }

    public Observable<WeatherData> getWeatherDataByTimeObservable(Long dt, Long cityId){
        return Observable.just(daoSession
                .getWeatherDataDao()
                .queryBuilder()
                .where(
                        WeatherDataDao.Properties.CityId.eq(cityId),
                        WeatherDataDao.Properties.Dt.eq(dt))
                .unique());
    }

    public Observable<List<WeatherData>> getWeatherDataForDayObservable(Long cityId, Long dt, Long period){
        return Observable.just(daoSession.getWeatherDataDao().queryBuilder()
                .where(WeatherDataDao.Properties.CityId.eq(cityId), WeatherDataDao.Properties.Dt.ge(dt),
                        WeatherDataDao.Properties.Dt.le(dt + period))
                .list());
    }

}
