package com.example.nvdovin.weatherapp.database.model;


import com.example.nvdovin.weatherapp.database.dao.CityDao;
import com.example.nvdovin.weatherapp.database.dao.DaoSession;
import com.example.nvdovin.weatherapp.database.dao.WeatherDataDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

@Entity
public class City {

    private static final String CITY_ID = "cityId";
    @Id
    private Long id;
    private String name;
    private Double lat;
    private Double lon;
    @ToMany(referencedJoinProperty = CITY_ID)
    private List<WeatherData> weatherDataList;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 448079911)
    private transient CityDao myDao;


    @Generated(hash = 271905848)
    public City(Long id, String name, Double lat, Double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    @Generated(hash = 750791287)
    public City() {
    }


    @Keep
    public List<WeatherData> getRawWeatherList() {
        return weatherDataList;
    }

    @Keep
    public void setRawWeatherList(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return this.lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1559883618)
    public List<WeatherData> getWeatherDataList() {
        if (weatherDataList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WeatherDataDao targetDao = daoSession.getWeatherDataDao();
            List<WeatherData> weatherDataListNew = targetDao
                    ._queryCity_WeatherDataList(id);
            synchronized (this) {
                if (weatherDataList == null) {
                    weatherDataList = weatherDataListNew;
                }
            }
        }
        return weatherDataList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 738982531)
    public synchronized void resetWeatherDataList() {
        weatherDataList = null;
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 293508440)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCityDao() : null;
    }

}
