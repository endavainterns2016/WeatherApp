
package com.example.nvdovin.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Sys_ {

    private static final String POD = "pod";
    @Id(autoincrement = true)
    private Long id;

    @SerializedName(POD)
    @Expose
    @Property
    private String pod;

    @Generated(hash = 1822457940)
    public Sys_(Long id, String pod) {
        this.id = id;
        this.pod = pod;
    }

    @Generated(hash = 254666558)
    public Sys_() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPod() {
        return this.pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}
