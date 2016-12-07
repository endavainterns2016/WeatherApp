package com.example.nvdovin.weatherapp.presentation.details.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.data.model.WeatherData;
import com.example.nvdovin.weatherapp.domain.model.DailyForecast;
import com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils;
import com.example.nvdovin.weatherapp.domain.utils.sharedpreferences.SharedPrefs;
import com.example.nvdovin.weatherapp.domain.utils.temperature.TemperatureConverter;
import com.example.nvdovin.weatherapp.domain.utils.time.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.nvdovin.weatherapp.domain.utils.design.ImageUtils.FONTS_LOCATION;

class TodayForecastAdapter extends RecyclerView.Adapter<TodayForecastAdapter.TodayForecastViewHolder> {
    private static final String DATE_FORMAT = "EEE HH:mm";
    private DailyForecast dailyForecast;
    private SharedPrefs sharedPrefs;
    private ImageUtils imageUtils;
    private Context context;

    TodayForecastAdapter(DailyForecast dailyForecast, Context context, SharedPrefs sharedPrefs) {
        this.dailyForecast = dailyForecast;
        this.context = context;
        this.sharedPrefs = sharedPrefs;
        imageUtils = new ImageUtils();

    }

    @Override
    public TodayForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_today_forecast_recycler_row, parent, false);
        return new TodayForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodayForecastViewHolder holder, int position) {
        holder.bindData(dailyForecast.getWeatherDataList().get(position));

    }

    @Override
    public int getItemCount() {
        return dailyForecast.getWeatherDataList().size();
    }

    class TodayForecastViewHolder extends ViewHolder<WeatherData> {
        @BindView(R.id.detail_today_icon)
        TextView icon;
        @BindView(R.id.detail_today_temperature)
        TextView temperature;
        @BindView(R.id.detail_today_time)
        TextView time;
        TodayForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Typeface weatherFont = Typeface.createFromAsset(context.getAssets(), FONTS_LOCATION);
            icon.setTypeface(weatherFont);
        }

        @Override
        @SuppressWarnings("deprecation")
        public void bindData(WeatherData data) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                icon.setText(Html.fromHtml(context.getString(imageUtils.getIconResById(data.getWeatherIcon())), Html.FROM_HTML_MODE_LEGACY));
            } else {
                icon.setText(Html.fromHtml(context.getString(imageUtils.getIconResById(data.getWeatherIcon()))));
            }
            temperature.setText(
                    TemperatureConverter
                            .fromId(sharedPrefs.getTempScaleFromPrefs())
                            .convertToTemperature(data.getTemp().intValue())
            );
            time.setText(TimeUtils.convertDateToFormat(TimeUtils.convertLongToDate(data.getDt()), DATE_FORMAT));
        }
    }
}
