
package com.example.nvdovin.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Wind {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("speed")
    @Expose
    @Property
    private Double speed;
    @SerializedName("deg")
    @Expose
    @Property
    private Double deg;
    @Generated(hash = 173722645)
    public Wind(Long id, Double speed, Double deg) {
        this.id = id;
        this.speed = speed;
        this.deg = deg;
    }
    @Generated(hash = 1286276427)
    public Wind() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getSpeed() {
        return this.speed;
    }
    public void setSpeed(Double speed) {
        this.speed = speed;
    }
    public Double getDeg() {
        return this.deg;
    }
    public void setDeg(Double deg) {
        this.deg = deg;
    }

}
