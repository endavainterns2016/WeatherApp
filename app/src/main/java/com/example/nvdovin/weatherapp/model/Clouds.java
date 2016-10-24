
package com.example.nvdovin.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Clouds {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("all")
    @Expose
    @Property
    private Integer all;

    @Generated(hash = 1486406842)
    public Clouds(Long id, Integer all) {
        this.id = id;
        this.all = all;
    }

    @Generated(hash = 158594515)
    public Clouds() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAll() {
        return this.all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

}
