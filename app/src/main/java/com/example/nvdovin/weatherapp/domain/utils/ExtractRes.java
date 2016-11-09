package com.example.nvdovin.weatherapp.domain.utils;

import android.content.Context;

public class ExtractRes {
    public static String getResource(Context context, int resId)
    {
        return context.getResources().getString(resId);
    }
}
