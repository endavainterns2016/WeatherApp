package com.example.nvdovin.weatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nvdovin.weatherapp.R;
import com.example.nvdovin.weatherapp.database.model.City;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
    private List<City> cityList = new ArrayList<City>();
    private int tempScale;

    public RecyclerViewAdapter(List<City> cityList, int tempScale) {
        this.cityList = cityList;
        this.tempScale = tempScale;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_recycler_row, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.cityName.setText(city.getName());
        holder.cityWeatherDescription.setText(city.getWeatherDataList().get(0).getWeatherDescription());
        holder.cityTemperature.setText(String.valueOf(city.getWeatherDataList().get(0).getTemp().intValue() - tempScale));
        holder.cityID = city.getId();
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView cityName, cityTemperature, cityWeatherDescription;
        Long cityID;

        public CustomViewHolder(View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.forecast_recycler_city_name);
            cityTemperature = (TextView) itemView.findViewById(R.id.forecast_recycler_city_temperature);
            cityWeatherDescription = (TextView) itemView.findViewById(R.id.forecast_recycler_city_weather);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //To be edited
                    Toast.makeText(v.getContext(), "You have selected " + cityName.getText() + " with the id - " + cityID, Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
