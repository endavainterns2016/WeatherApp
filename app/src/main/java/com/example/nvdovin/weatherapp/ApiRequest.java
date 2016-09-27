package com.example.nvdovin.myopenweatherexample;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nvdovin on 9/27/2016.
 */

public class ApiRequest {

    private static final String OPEN_WEATHER_MAP_URL =
            //"http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric";
            "http://api.openweathermap.org/data/2.5/group?id=%s&units=metric";

    private static final String OPEN_WEATHER_MAP_API = "9751779db2d9af6a5c10f9db7b48802f";


    public static JSONObject getWeatherJSON(String... params){
        String coords = "";

        for (int i = 0; i < params.length; i++) {
            coords += params[i];
            if(i != params.length - 1){
                coords += ",";
            }
        }

        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_URL, coords));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("x-api-key", OPEN_WEATHER_MAP_API);
//            System.out.println(connection.toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp ="";
            while ((tmp=reader.readLine()) != null){
                json.append(tmp).append("\n");
            }
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


}
