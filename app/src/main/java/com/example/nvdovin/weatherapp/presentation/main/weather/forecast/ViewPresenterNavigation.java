package com.example.nvdovin.weatherapp.presentation.main.weather.forecast;

import rx.Observable;

public interface ViewPresenterNavigation {
    void navigationButtonHandler(Long cityId);
    void passClickHandlerObservable(Observable<Long> clickObservable);
}
