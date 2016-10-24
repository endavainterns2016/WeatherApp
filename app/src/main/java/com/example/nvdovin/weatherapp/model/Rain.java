
package com.example.nvdovin.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Rain {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("3h")
    @Expose
    @Property
    private Double _3h;

    @Generated(hash = 437499481)
    public Rain(Long id, Double _3h) {
        this.id = id;
        this._3h = _3h;
    }

    @Generated(hash = 1071394187)
    public Rain() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double get_3h() {
        return this._3h;
    }

    public void set_3h(Double _3h) {
        this._3h = _3h;
    }

}
