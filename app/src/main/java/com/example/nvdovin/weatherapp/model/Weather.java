
package com.example.nvdovin.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Weather {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("id")
    @Expose
    @Property
    private Integer weatherId;
    @SerializedName("main")
    @Expose
    @Property
    private String main;
    @SerializedName("description")
    @Expose
    @Property
    private String description;
    @SerializedName("icon")
    @Expose
    @Property
    private String icon;
    @Generated(hash = 2007035224)
    public Weather(Long id, Integer weatherId, String main, String description,
            String icon) {
        this.id = id;
        this.weatherId = weatherId;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }
    @Generated(hash = 556711069)
    public Weather() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getWeatherId() {
        return this.weatherId;
    }
    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }
    public String getMain() {
        return this.main;
    }
    public void setMain(String main) {
        this.main = main;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

}