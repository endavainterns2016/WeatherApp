
package com.example.nvdovin.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Main {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("temp")
    @Expose
    @Property
    private Double temp;
    @SerializedName("temp_min")
    @Expose
    @Property
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    @Property
    private Double tempMax;
    @SerializedName("pressure")
    @Expose
    @Property
    private Double pressure;
    @SerializedName("sea_level")
    @Expose
    @Property
    private Double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    @Property
    private Double grndLevel;
    @SerializedName("humidity")
    @Expose
    @Property
    private Integer humidity;
    @SerializedName("temp_kf")
    @Expose
    @Property
    private Double tempKf;

    @Generated(hash = 1063264703)
    public Main(Long id, Double temp, Double tempMin, Double tempMax,
            Double pressure, Double seaLevel, Double grndLevel, Integer humidity,
            Double tempKf) {
        this.id = id;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.seaLevel = seaLevel;
        this.grndLevel = grndLevel;
        this.humidity = humidity;
        this.tempKf = tempKf;
    }
    @Generated(hash = 1298277417)
    public Main() {
    }
   
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getTemp() {
        return this.temp;
    }
    public void setTemp(Double temp) {
        this.temp = temp;
    }
    public Double getTempMin() {
        return this.tempMin;
    }
    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }
    public Double getTempMax() {
        return this.tempMax;
    }
    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }
    public Double getPressure() {
        return this.pressure;
    }
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }
    public Double getSeaLevel() {
        return this.seaLevel;
    }
    public void setSeaLevel(Double seaLevel) {
        this.seaLevel = seaLevel;
    }
    public Double getGrndLevel() {
        return this.grndLevel;
    }
    public void setGrndLevel(Double grndLevel) {
        this.grndLevel = grndLevel;
    }
    public Integer getHumidity() {
        return this.humidity;
    }
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
    public Double getTempKf() {
        return this.tempKf;
    }
    public void setTempKf(Double tempKf) {
        this.tempKf = tempKf;
    }

}
