package com.example.nvdovin.weatherapp.domain.utils.eventbus;

import org.greenrobot.eventbus.EventBus;

public class EventBusWrapper {

    private EventBus eventBus;

    public EventBusWrapper() {
        eventBus = EventBus.getDefault();
    }

    public void register(Object object) {
        eventBus.register(object);
    }

    public void post(Object object) {
        eventBus.post(object);
    }
}
