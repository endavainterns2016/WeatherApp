package com.example.nvdovin.weatherapp.domain.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor {

    public static final int MILI_VALUE = 1000;

    public String convertDateToFormat(Date date, String format)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public Long convertDateToLong (Date date)
    {
        return date.getTime();
    }

    public Date convertLongToDate (Long dt)
    {
        return new Date(dt*1000);
    }
    public Long milisecToSec(long milisec){
      return milisec/ MILI_VALUE;

    }
}
