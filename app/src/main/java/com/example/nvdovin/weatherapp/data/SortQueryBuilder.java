package com.example.nvdovin.weatherapp.data;

import org.greenrobot.greendao.Property;

public class SortQueryBuilder<T extends Property> {

    private T property;
    private boolean ascending;

    public T getProperty() {
        return property;
    }

    public void setProperty(T property) {
        this.property = property;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}


