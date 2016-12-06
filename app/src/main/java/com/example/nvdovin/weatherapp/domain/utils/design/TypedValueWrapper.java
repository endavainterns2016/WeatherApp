package com.example.nvdovin.weatherapp.domain.utils.design;

import android.content.Context;
import android.util.TypedValue;

public class TypedValueWrapper {

    public float getThickness(float heightDp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                heightDp, context.getResources().getDisplayMetrics());
    }

}
