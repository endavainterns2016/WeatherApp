package com.example.nvdovin.weatherapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mcebotari on 10/24/2016.
 */

public interface RetrofitInterfaceAPI {

    @GET("forecast")
    Call<RetrofitResponse> getAPIResponse(@Query("appid") String API_KEY, @Query("q") String query_city_name);

}
