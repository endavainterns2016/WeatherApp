package com.example.nvdovin.weatherapp.presentation.main.weather.detail;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.utils.mapper.WeatherCodesMap;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.temperature.TemperatureConvertor;
import com.example.nvdovin.weatherapp.fragments.cityDetails.adapter.TemperatureRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsFragment extends Fragment implements DetailsView {

    private static final String CITY_ID_KEY = "CITY_ID_KEY";
    private static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";
    private static final String FONTS_LOCATION = "fonts/weathericons-regular-webfont.ttf";
    @BindView(R.id.city_details_icon)
    TextView icon;
    @BindView(R.id.city_details_name)
    TextView cityName;
    @BindView(R.id.city_details_recycler_temperature)
    RecyclerView recyclerView;
    @BindView(R.id.city_details_temperature)
    TextView cityTemperature;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_details_fragment, container, false);
        ButterKnife.bind(this, view);
        Typeface weatherFont;
        this.context = view.getContext();
        SharedPrefs sharedPrefs = new SharedPrefs(view.getContext());


        weatherFont = Typeface.createFromAsset(view.getContext().getAssets(), FONTS_LOCATION);
        icon.setTypeface(weatherFont);

        Long cityId = getArguments().getLong(CITY_ID_KEY);
        Long timestamp = getArguments().getLong(TIMESTAMP_KEY);

        DetailsPresenter detailsPresenter = new DetailsPresenter(cityId, timestamp, view.getContext());
        WeatherData currentWeatherData = detailsPresenter.getIdByDt(cityId, timestamp);
        setWeatherIcon(currentWeatherData.getWeatherIcon(), icon);
        cityName.setText(detailsPresenter.getCityName(cityId));
        cityTemperature = (TextView) view.findViewById(R.id.city_details_temperature);
        cityTemperature.setText(String.valueOf(
                TemperatureConvertor
                        .fromId(sharedPrefs.getTempScaleFromPrefs())
                        .convertToTemperature(currentWeatherData.getTemp().intValue())
        ));

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new TemperatureRecyclerAdapter(detailsPresenter.getForecast(), context));

        return view;
    }

    private int setWeatherIcon(String id, TextView iconTextView) {
        WeatherCodesMap weatherCodesMap = new WeatherCodesMap();
        int resID = weatherCodesMap.getBackgroundResById(id);
        String icon = context.getString(weatherCodesMap.getIconByID(id));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            iconTextView.setText(Html.fromHtml(icon, Html.FROM_HTML_MODE_LEGACY));
        else
            iconTextView.setText(Html.fromHtml(icon));
        return resID;
    }
}
