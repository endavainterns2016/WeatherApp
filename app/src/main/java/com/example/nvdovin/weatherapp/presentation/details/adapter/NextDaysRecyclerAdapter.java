package com.example.nvdovin.weatherapp.presentation.details.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.utils.date.DateConvertor;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.temperature.TemperatureConvertor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class NextDaysRecyclerAdapter extends RecyclerView.Adapter<NextDaysRecyclerAdapter.CustomViewHolder> {

    private static final int NUMBER_OF_DAYS_TO_FORECAST = 4;
    private static final String DATE_FORMAT = "EEEE";
    private List<DailyForecast> dailyForecastList;
    private DateConvertor dateConvertor;
    private SharedPrefs sharedPrefs;

    NextDaysRecyclerAdapter(List<DailyForecast> dailyForecastList, Context context) {
        this.dailyForecastList = dailyForecastList;
        dateConvertor = new DateConvertor();
        sharedPrefs = new SharedPrefs(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_five_days_recycler_row, parent, false);
        return new NextDaysRecyclerAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        DailyForecast dailyForecast = dailyForecastList.get(position);
        if (position == 0) {
            holder.name.setText(R.string.today);
        } else if (position == 1) {
            holder.name.setText(R.string.tommorow);
        } else {
            holder.name.setText(dateConvertor.convertDateToFormat(dailyForecast.getDate(), DATE_FORMAT));
        }
        holder.temperature.setText(TextUtils.concat(
                TemperatureConvertor
                        .fromId(sharedPrefs.getTempScaleFromPrefs())
                        .convertToTemperature(dailyForecast.getDayTempMin()), " ~ ",

                        TemperatureConvertor
                                .fromId(sharedPrefs.getTempScaleFromPrefs())
                                .convertToTemperature(dailyForecast.getDayTempMax()))
        );
    }

    @Override
    public int getItemCount() {
        return NUMBER_OF_DAYS_TO_FORECAST;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.detail_five_days_day_name)
        TextView name;
        @BindView(R.id.detail_five_days_temperatures)
        TextView temperature;

        CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
