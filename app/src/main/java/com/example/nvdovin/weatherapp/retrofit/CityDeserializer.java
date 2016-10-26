package com.example.nvdovin.weatherapp.retrofit;

import com.example.nvdovin.weatherapp.model.City;
import com.example.nvdovin.weatherapp.model.WeatherData;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CityDeserializer implements JsonDeserializer<City> {
    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        City c = new City();
        List<WeatherData> weatherDataList = new ArrayList<>();

        JsonObject cityObject = json.getAsJsonObject();
        JsonElement cityElement = cityObject.get("city");
        JsonObject city = cityElement.getAsJsonObject();
        JsonElement name = city.get("name");
        JsonElement id = city.get("id");
        JsonElement coordElement = city.get("coord");
        JsonObject coord = coordElement.getAsJsonObject();
        JsonElement lon = coord.get("lon");
        JsonElement lat = coord.get("lat");

        JsonElement weatherListElement = cityObject.get("list");

        JsonArray weatherListArray = weatherListElement.getAsJsonArray();
        for (JsonElement element : weatherListArray) {
            JsonObject data = element.getAsJsonObject();
            JsonElement dateElem = data.get("dt");
            JsonElement mainElem = data.get("main");
            JsonObject main = mainElem.getAsJsonObject();
            JsonElement temp = main.get("temp");
            JsonElement tempMin = main.get("temp_min");
            JsonElement tempMax = main.get("temp_max");
            JsonElement pressure = main.get("pressure");
            JsonElement humidity = main.get("humidity");
            JsonElement weather = data.get("weather");
            JsonArray weatherArray = weather.getAsJsonArray();
            JsonElement oneElement = weatherArray.get(0);
            JsonObject weatherArrayBody = oneElement.getAsJsonObject();
            JsonElement weatherMain = weatherArrayBody.get("main");
            JsonElement weatherDescription = weatherArrayBody.get("description");
            JsonElement weatherIcon = weatherArrayBody.get("icon");

            JsonElement cloudsElem = data.get("clouds");
            JsonObject cloudsBody = cloudsElem.getAsJsonObject();
            JsonElement clouds = cloudsBody.get("all");

            JsonElement windElem = data.get("wind");
            JsonObject windBody = windElem.getAsJsonObject();
            JsonElement wind = windBody.get("speed");

            JsonElement rainElem = data.get("rain");
            JsonObject rainBody = rainElem.getAsJsonObject();
            JsonElement rain = rainBody.get("3h");

            WeatherData weatherData = new WeatherData();
            weatherData.setDt(dateElem.getAsLong());
            String uniqueId = id.getAsString() + dateElem.getAsString();
            weatherData.setUniqueId(uniqueId);
            weatherData.setTemp(temp.getAsDouble());
            weatherData.setTempMin(tempMin.getAsDouble());
            weatherData.setTempMax(tempMax.getAsDouble());
            weatherData.setHumidity(humidity.getAsDouble());
            weatherData.setPressure(pressure.getAsDouble());
            weatherData.setWeather(weatherMain.getAsString());
            weatherData.setWeatherDescription(weatherDescription.getAsString());
            weatherData.setWeatherIcon(weatherIcon.getAsString());
            weatherData.setClouds(clouds.getAsDouble());
            weatherData.setWindSpeed(wind.getAsDouble());
            if (rain != null)
                weatherData.setRain(rain.getAsDouble());
            weatherDataList.add(weatherData);
        }

        c.setId(id.getAsLong());
        c.setName(name.getAsString());
        c.setLat(lat.getAsDouble());
        c.setLon(lon.getAsDouble());
        c.setRawWeatherList(weatherDataList);

        return c;
    }
}
