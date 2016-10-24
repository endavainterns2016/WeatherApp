
package com.example.nvdovin.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.nvdovin.weatherapp.GreenDao.DaoSession;
import com.example.nvdovin.weatherapp.GreenDao.SysDao;
import com.example.nvdovin.weatherapp.GreenDao.CoordDao;
import com.example.nvdovin.weatherapp.GreenDao.CityDao;

@Entity
public class City {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("id")
    @Expose
    @Property(nameInDb = "CITY_ID")
    private Long cityId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    @ToOne(joinProperty = "id")
    private Coord coord;
    @SerializedName("country")
    @Expose
    @Property
    private String country;
    @SerializedName("population")
    @Expose
    @Property
    private Integer population;
    @SerializedName("sys")
    @Expose
    @ToOne(joinProperty = "id")
    private Sys sys;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 448079911)
    private transient CityDao myDao;

    @Generated(hash = 1886766553)
    public City(Long id, Long cityId, String name, String country,
            Integer population) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.country = country;
        this.population = population;
    }

    @Generated(hash = 750791287)
    public City() {
    }

    @Generated(hash = 439018334)
    private transient Long coord__resolvedKey;

    @Generated(hash = 828932504)
    private transient Long sys__resolvedKey;

    @Keep
    public Coord getRawCoord() {
        return coord;
    }

    @Keep
    public Sys getRawSys() {
        return sys;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return this.cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPopulation() {
        return this.population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1447328721)
    public Coord getCoord() {
        Long __key = this.id;
        if (coord__resolvedKey == null || !coord__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CoordDao targetDao = daoSession.getCoordDao();
            Coord coordNew = targetDao.load(__key);
            synchronized (this) {
                coord = coordNew;
                coord__resolvedKey = __key;
            }
        }
        return coord;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2014193862)
    public void setCoord(Coord coord) {
        synchronized (this) {
            this.coord = coord;
            id = coord == null ? null : coord.getId();
            coord__resolvedKey = id;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 301207718)
    public Sys getSys() {
        Long __key = this.id;
        if (sys__resolvedKey == null || !sys__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SysDao targetDao = daoSession.getSysDao();
            Sys sysNew = targetDao.load(__key);
            synchronized (this) {
                sys = sysNew;
                sys__resolvedKey = __key;
            }
        }
        return sys;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1572248187)
    public void setSys(Sys sys) {
        synchronized (this) {
            this.sys = sys;
            id = sys == null ? null : sys.getId();
            sys__resolvedKey = id;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 293508440)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCityDao() : null;
    }
}
