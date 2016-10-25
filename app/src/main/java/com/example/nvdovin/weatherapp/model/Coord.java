
package com.example.nvdovin.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Coord {

    private static final String LON = "lon";
    private static final String LAT = "lat";
    @Id(autoincrement = true)
    private Long id;

    @SerializedName(LON)
    @Expose
    @Property
    private Double lon;
    @SerializedName(LAT)
    @Expose
    @Property
    private Double lat;
    @Generated(hash = 881810136)
    public Coord(Long id, Double lon, Double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }
    @Generated(hash = 826419929)
    public Coord() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getLon() {
        return this.lon;
    }
    public void setLon(Double lon) {
        this.lon = lon;
    }
    public Double getLat() {
        return this.lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }

}
