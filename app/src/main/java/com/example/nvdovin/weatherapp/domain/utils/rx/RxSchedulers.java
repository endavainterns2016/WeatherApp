package com.example.nvdovin.weatherapp.domain.utils.rx;

import rx.Scheduler;

public interface RxSchedulers {

    Scheduler androidUI();

    Scheduler io();

    Scheduler computation();

    Scheduler network();

    Scheduler immediate();
}