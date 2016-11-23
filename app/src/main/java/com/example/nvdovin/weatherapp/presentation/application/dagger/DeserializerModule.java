package com.example.nvdovin.weatherapp.presentation.application.dagger;


import com.example.nvdovin.weatherapp.data.model.City;
import com.example.nvdovin.weatherapp.domain.deserialize.CityDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

@Module
public class DeserializerModule {

    @Provides
    Gson provideGson(CityDeserializer cityDeserializer) {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(City.class, cityDeserializer)
                .create();
    }

    @Provides
    CityDeserializer provideCityDeserializer() {
        return new CityDeserializer();
    }

}
