
package com.example.nvdovin.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Sys {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("population")
    @Expose
    @Property
    private Integer population;

    @Generated(hash = 1811687350)
    public Sys(Long id, Integer population) {
        this.id = id;
        this.population = population;
    }

    @Generated(hash = 404025715)
    public Sys() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPopulation() {
        return this.population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

}
