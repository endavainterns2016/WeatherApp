package com.example.nvdovin.weatherapp.Retrofit;

/**
 * Created by mcebotari on 10/24/2016.
 */
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.util.ArrayList;

public class RetrofitResponse {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Float message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.example.nvdovin.weatherapp.Retrofit.List> list = new ArrayList<com.example.nvdovin.weatherapp.Retrofit.List>();

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Float getMessage() {
        return message;
    }

    public void setMessage(Float message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.example.nvdovin.weatherapp.Retrofit.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.example.nvdovin.weatherapp.Retrofit.List> list) {
        this.list = list;
    }

}

