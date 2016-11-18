package com.example.nvdovin.weatherapp.presentation.details.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.temperature.TemperatureConverter;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class NextDaysRecyclerAdapter extends RecyclerView.Adapter<NextDaysRecyclerAdapter.NextDaysViewHolder> {

    private static final int NUMBER_OF_DAYS_TO_FORECAST = 4;
    private static final String DATE_FORMAT = "EEEE";
    private static final int TOMMOROW_ADD_DAY = 1;
    private static final int YESTERDAY_SUBSTRACT_DAY = -1;
    private List<DailyForecast> dailyForecastList;
    private SharedPrefs sharedPrefs;
    private Context context;


    NextDaysRecyclerAdapter(List<DailyForecast> dailyForecastList, Context context) {
        this.dailyForecastList = dailyForecastList;
        sharedPrefs = new SharedPrefs(context);
        this.context = context;
    }

    @Override
    public NextDaysViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_five_days_recycler_row, parent, false);
        return new NextDaysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NextDaysViewHolder holder, int position) {
        holder.bindData(dailyForecastList.get(position));
    }

    @Override
    public int getItemCount() {
        return NUMBER_OF_DAYS_TO_FORECAST;
    }

    class NextDaysViewHolder extends ViewHolder<DailyForecast> {
        @BindView(R.id.detail_five_days_day_name)
        TextView name;
        @BindView(R.id.detail_five_days_temperatures)
        TextView temperature;

        NextDaysViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindData(DailyForecast data) {
            name.setText(getDayString(data.getDate()));
            temperature.setText(TextUtils.concat(
                    TemperatureConverter
                            .fromId(sharedPrefs.getTempScaleFromPrefs())
                            .convertToTemperature(data.getDayTempMin()), " ~ ",

                    TemperatureConverter
                            .fromId(sharedPrefs.getTempScaleFromPrefs())
                            .convertToTemperature(data.getDayTempMax()))
            );
        }
    }

    private String getDayString(Date dateToCheck){
        String dayString = "";
        Date todayDate = TimeUtils.getTodayDate();

        if (TimeUtils.isSameDate(dateToCheck, TimeUtils.addDaysToDate(todayDate, YESTERDAY_SUBSTRACT_DAY))){
                dayString = context.getResources().getString(R.string.yesterday);
            }
        else if(DateUtils.isToday(dateToCheck.getTime())) {
                dayString = context.getResources().getString(R.string.today);
            }
        else if(TimeUtils.isSameDate(dateToCheck, TimeUtils.addDaysToDate(todayDate, TOMMOROW_ADD_DAY))){
            dayString = context.getResources().getString(R.string.tommorow);
        } else {
            dayString = TimeUtils.convertDateToFormat(dateToCheck, DATE_FORMAT);
        }
        return dayString;
    }

}
