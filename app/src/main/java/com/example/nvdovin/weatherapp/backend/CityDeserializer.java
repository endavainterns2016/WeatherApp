package com.example.nvdovin.weatherapp.backend;

import com.example.nvdovin.weatherapp.database.model.City;
import com.example.nvdovin.weatherapp.database.model.WeatherData;
import com.example.nvdovin.weatherapp.utils.Mapper;
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


    private static final String CITY = "city";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String COORD = "coord";
    private static final String LON = "lon";
    private static final String LAT = "lat";
    private static final String LIST = "list";
    private static final String DT = "dt";
    private static final String MAIN = "main";
    private static final String TEMP = "temp";
    private static final String TEMP_MIN = "temp_min";
    private static final String TEMP_MAX = "temp_max";
    private static final String PRESSURE = "pressure";
    private static final String HUMIDITY = "humidity";
    private static final String WEATHER = "weather";
    private static final String DESCRIPTION = "description";
    private static final String ICON = "icon";
    private static final String CLOUDS = "clouds";
    private static final String ALL = "all";
    private static final String WIND = "wind";
    private static final String SPEED = "speed";
    private static final String RAIN = "rain";
    private static final String H = "3h";

    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        City c = new City();
        List<WeatherData> weatherDataList = new ArrayList<>();

        JsonObject cityObject = json.getAsJsonObject();
        JsonElement cityElement = cityObject.get(CITY);
        JsonObject city = cityElement.getAsJsonObject();
        JsonElement name = city.get(NAME);
        JsonElement id = city.get(ID);
        JsonElement coordElement = city.get(COORD);
        JsonObject coord = coordElement.getAsJsonObject();
        JsonElement lon = coord.get(LON);
        JsonElement lat = coord.get(LAT);

        JsonElement weatherListElement = cityObject.get(LIST);

        JsonArray weatherListArray = weatherListElement.getAsJsonArray();
        for (JsonElement element : weatherListArray) {
            JsonObject data = element.getAsJsonObject();
            JsonElement dateElem = data.get(DT);
            JsonElement mainElem = data.get(MAIN);
            JsonObject main = mainElem.getAsJsonObject();
            JsonElement temp = main.get(TEMP);
            JsonElement tempMin = main.get(TEMP_MIN);
            JsonElement tempMax = main.get(TEMP_MAX);
            JsonElement pressure = main.get(PRESSURE);
            JsonElement humidity = main.get(HUMIDITY);
            JsonElement weather = data.get(WEATHER);
            JsonArray weatherArray = weather.getAsJsonArray();
            JsonElement oneElement = weatherArray.get(0);
            JsonObject weatherArrayBody = oneElement.getAsJsonObject();
            JsonElement weatherMain = weatherArrayBody.get(MAIN);
            JsonElement weatherDescription = weatherArrayBody.get(DESCRIPTION);
            JsonElement weatherIcon = weatherArrayBody.get(ICON);

            JsonElement cloudsElem = data.get(CLOUDS);
            JsonObject cloudsBody = cloudsElem.getAsJsonObject();
            JsonElement clouds = cloudsBody.get(ALL);

            JsonElement windElem = data.get(WIND);
            JsonObject windBody = windElem.getAsJsonObject();
            JsonElement wind = windBody.get(SPEED);

            JsonElement rainElem = data.get(RAIN);
            JsonObject rainBody;
            JsonElement rain = null;
            if (rainElem != null) {
                rainBody = rainElem.getAsJsonObject();
                rain = rainBody.get(H);
            }

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
            weatherData.setCityId(id.getAsLong());
        }

        c.setId(id.getAsLong());
        c.setName(name.getAsString());
        c.setLat(lat.getAsDouble());
        c.setLon(lon.getAsDouble());
        c.setRawWeatherList(Mapper.updatedWeatherData(weatherDataList));

        return c;
    }
}
