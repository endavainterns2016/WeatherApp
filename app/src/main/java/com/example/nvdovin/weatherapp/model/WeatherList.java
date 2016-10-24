
package com.example.nvdovin.weatherapp.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.example.nvdovin.weatherapp.greendao.DaoSession;
import com.example.nvdovin.weatherapp.greendao.WeatherDao;
import com.example.nvdovin.weatherapp.greendao.RainDao;
import com.example.nvdovin.weatherapp.greendao.Sys_Dao;
import com.example.nvdovin.weatherapp.greendao.WindDao;
import com.example.nvdovin.weatherapp.greendao.CloudsDao;
import com.example.nvdovin.weatherapp.greendao.MainDao;
import com.example.nvdovin.weatherapp.greendao.WeatherListDao;

@Entity
public class WeatherList {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("dt")
    @Expose
    @Property
    private Long dt;
    @SerializedName("main")
    @Expose
    @ToOne(joinProperty = "id")
    private Main main;
    @SerializedName("weather")
    @Expose
    @ToMany(referencedJoinProperty = "id")
    private java.util.List<Weather> weather = new ArrayList<Weather>();
    @SerializedName("clouds")
    @Expose
    @ToOne(joinProperty = "id")
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    @ToOne(joinProperty = "id")
    private Wind wind;
    @SerializedName("sys")
    @Expose
    @ToOne(joinProperty = "id")
    private Sys_ sys;
    @SerializedName("dt_txt")
    @Expose
    @Property
    private String dtTxt;
    @SerializedName("rain")
    @Expose
    @ToOne(joinProperty = "id")
    private Rain rain;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1086981352)
    private transient WeatherListDao myDao;

    @Generated(hash = 226866220)
    public WeatherList(Long id, Long dt, String dtTxt) {
        this.id = id;
        this.dt = dt;
        this.dtTxt = dtTxt;
    }

    @Generated(hash = 1405123287)
    public WeatherList() {
    }

    @Generated(hash = 352896832)
    private transient Long main__resolvedKey;

    @Generated(hash = 2129291810)
    private transient Long clouds__resolvedKey;

    @Generated(hash = 686833448)
    private transient Long wind__resolvedKey;

    @Generated(hash = 828932504)
    private transient Long sys__resolvedKey;

    @Generated(hash = 167786990)
    private transient Long rain__resolvedKey;

    @Keep
    public Main getRawMain() {
        return main;
    }

    @Keep
    public List<Weather> getRawWeather() {
        return weather;
    }

    @Keep
    public Clouds getRawClouds() {
        return clouds;
    }

    @Keep
    public Wind getRawWind() {
        return wind;
    }

    @Keep
    public Sys_ getRawSys() {
        return sys;
    }

    @Keep
    public Rain getRawRain() {
        return rain;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDt() {
        return this.dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public String getDtTxt() {
        return this.dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1023249039)
    public Main getMain() {
        Long __key = this.id;
        if (main__resolvedKey == null || !main__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MainDao targetDao = daoSession.getMainDao();
            Main mainNew = targetDao.load(__key);
            synchronized (this) {
                main = mainNew;
                main__resolvedKey = __key;
            }
        }
        return main;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 844448176)
    public void setMain(Main main) {
        synchronized (this) {
            this.main = main;
            id = main == null ? null : main.getId();
            main__resolvedKey = id;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 535609347)
    public Clouds getClouds() {
        Long __key = this.id;
        if (clouds__resolvedKey == null || !clouds__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CloudsDao targetDao = daoSession.getCloudsDao();
            Clouds cloudsNew = targetDao.load(__key);
            synchronized (this) {
                clouds = cloudsNew;
                clouds__resolvedKey = __key;
            }
        }
        return clouds;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 243268015)
    public void setClouds(Clouds clouds) {
        synchronized (this) {
            this.clouds = clouds;
            id = clouds == null ? null : clouds.getId();
            clouds__resolvedKey = id;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 7177231)
    public Wind getWind() {
        Long __key = this.id;
        if (wind__resolvedKey == null || !wind__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WindDao targetDao = daoSession.getWindDao();
            Wind windNew = targetDao.load(__key);
            synchronized (this) {
                wind = windNew;
                wind__resolvedKey = __key;
            }
        }
        return wind;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1476129710)
    public void setWind(Wind wind) {
        synchronized (this) {
            this.wind = wind;
            id = wind == null ? null : wind.getId();
            wind__resolvedKey = id;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1930338848)
    public Sys_ getSys() {
        Long __key = this.id;
        if (sys__resolvedKey == null || !sys__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Sys_Dao targetDao = daoSession.getSys_Dao();
            Sys_ sysNew = targetDao.load(__key);
            synchronized (this) {
                sys = sysNew;
                sys__resolvedKey = __key;
            }
        }
        return sys;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1209424877)
    public void setSys(Sys_ sys) {
        synchronized (this) {
            this.sys = sys;
            id = sys == null ? null : sys.getId();
            sys__resolvedKey = id;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 412002898)
    public Rain getRain() {
        Long __key = this.id;
        if (rain__resolvedKey == null || !rain__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RainDao targetDao = daoSession.getRainDao();
            Rain rainNew = targetDao.load(__key);
            synchronized (this) {
                rain = rainNew;
                rain__resolvedKey = __key;
            }
        }
        return rain;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 898592342)
    public void setRain(Rain rain) {
        synchronized (this) {
            this.rain = rain;
            id = rain == null ? null : rain.getId();
            rain__resolvedKey = id;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 40176618)
    public List<Weather> getWeather() {
        if (weather == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WeatherDao targetDao = daoSession.getWeatherDao();
            List<Weather> weatherNew = targetDao._queryWeatherList_Weather(id);
            synchronized (this) {
                if (weather == null) {
                    weather = weatherNew;
                }
            }
        }
        return weather;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1453208750)
    public synchronized void resetWeather() {
        weather = null;
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
    @Generated(hash = 363406127)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWeatherListDao() : null;
    }

}
