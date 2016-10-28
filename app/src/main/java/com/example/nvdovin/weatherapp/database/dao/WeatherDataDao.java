package com.example.nvdovin.weatherapp.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.example.nvdovin.weatherapp.database.model.WeatherData;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "WEATHER_DATA".
 */
public class WeatherDataDao extends AbstractDao<WeatherData, Long> {

    public static final String TABLENAME = "WEATHER_DATA";

    /**
     * Properties of entity WeatherData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CityId = new Property(1, Long.class, "cityId", false, "CITY_ID");
        public final static Property UniqueId = new Property(2, String.class, "uniqueId", false, "UNIQUE_ID");
        public final static Property Dt = new Property(3, Long.class, "dt", false, "DT");
        public final static Property Temp = new Property(4, Double.class, "temp", false, "TEMP");
        public final static Property TempMin = new Property(5, Double.class, "tempMin", false, "TEMP_MIN");
        public final static Property TempMax = new Property(6, Double.class, "tempMax", false, "TEMP_MAX");
        public final static Property Pressure = new Property(7, Double.class, "pressure", false, "PRESSURE");
        public final static Property Humidity = new Property(8, Double.class, "humidity", false, "HUMIDITY");
        public final static Property Weather = new Property(9, String.class, "weather", false, "WEATHER");
        public final static Property WeatherDescription = new Property(10, String.class, "weatherDescription", false, "WEATHER_DESCRIPTION");
        public final static Property WeatherIcon = new Property(11, String.class, "weatherIcon", false, "WEATHER_ICON");
        public final static Property Clouds = new Property(12, Double.class, "clouds", false, "CLOUDS");
        public final static Property WindSpeed = new Property(13, Double.class, "windSpeed", false, "WIND_SPEED");
        public final static Property Rain = new Property(14, Double.class, "rain", false, "RAIN");
    }

    private Query<WeatherData> city_WeatherDataListQuery;

    public WeatherDataDao(DaoConfig config) {
        super(config);
    }

    public WeatherDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"WEATHER_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CITY_ID\" INTEGER," + // 1: cityId
                "\"UNIQUE_ID\" TEXT," + // 2: uniqueId
                "\"DT\" INTEGER," + // 3: dt
                "\"TEMP\" REAL," + // 4: temp
                "\"TEMP_MIN\" REAL," + // 5: tempMin
                "\"TEMP_MAX\" REAL," + // 6: tempMax
                "\"PRESSURE\" REAL," + // 7: pressure
                "\"HUMIDITY\" REAL," + // 8: humidity
                "\"WEATHER\" TEXT," + // 9: weather
                "\"WEATHER_DESCRIPTION\" TEXT," + // 10: weatherDescription
                "\"WEATHER_ICON\" TEXT," + // 11: weatherIcon
                "\"CLOUDS\" REAL," + // 12: clouds
                "\"WIND_SPEED\" REAL," + // 13: windSpeed
                "\"RAIN\" REAL);"); // 14: rain
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_WEATHER_DATA_UNIQUE_ID ON WEATHER_DATA" +
                " (\"UNIQUE_ID\" ASC);");
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WEATHER_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, WeatherData entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        Long cityId = entity.getCityId();
        if (cityId != null) {
            stmt.bindLong(2, cityId);
        }

        String uniqueId = entity.getUniqueId();
        if (uniqueId != null) {
            stmt.bindString(3, uniqueId);
        }

        Long dt = entity.getDt();
        if (dt != null) {
            stmt.bindLong(4, dt);
        }

        Double temp = entity.getTemp();
        if (temp != null) {
            stmt.bindDouble(5, temp);
        }

        Double tempMin = entity.getTempMin();
        if (tempMin != null) {
            stmt.bindDouble(6, tempMin);
        }

        Double tempMax = entity.getTempMax();
        if (tempMax != null) {
            stmt.bindDouble(7, tempMax);
        }

        Double pressure = entity.getPressure();
        if (pressure != null) {
            stmt.bindDouble(8, pressure);
        }

        Double humidity = entity.getHumidity();
        if (humidity != null) {
            stmt.bindDouble(9, humidity);
        }

        String weather = entity.getWeather();
        if (weather != null) {
            stmt.bindString(10, weather);
        }

        String weatherDescription = entity.getWeatherDescription();
        if (weatherDescription != null) {
            stmt.bindString(11, weatherDescription);
        }

        String weatherIcon = entity.getWeatherIcon();
        if (weatherIcon != null) {
            stmt.bindString(12, weatherIcon);
        }

        Double clouds = entity.getClouds();
        if (clouds != null) {
            stmt.bindDouble(13, clouds);
        }

        Double windSpeed = entity.getWindSpeed();
        if (windSpeed != null) {
            stmt.bindDouble(14, windSpeed);
        }

        Double rain = entity.getRain();
        if (rain != null) {
            stmt.bindDouble(15, rain);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, WeatherData entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        Long cityId = entity.getCityId();
        if (cityId != null) {
            stmt.bindLong(2, cityId);
        }

        String uniqueId = entity.getUniqueId();
        if (uniqueId != null) {
            stmt.bindString(3, uniqueId);
        }

        Long dt = entity.getDt();
        if (dt != null) {
            stmt.bindLong(4, dt);
        }

        Double temp = entity.getTemp();
        if (temp != null) {
            stmt.bindDouble(5, temp);
        }

        Double tempMin = entity.getTempMin();
        if (tempMin != null) {
            stmt.bindDouble(6, tempMin);
        }

        Double tempMax = entity.getTempMax();
        if (tempMax != null) {
            stmt.bindDouble(7, tempMax);
        }

        Double pressure = entity.getPressure();
        if (pressure != null) {
            stmt.bindDouble(8, pressure);
        }

        Double humidity = entity.getHumidity();
        if (humidity != null) {
            stmt.bindDouble(9, humidity);
        }

        String weather = entity.getWeather();
        if (weather != null) {
            stmt.bindString(10, weather);
        }

        String weatherDescription = entity.getWeatherDescription();
        if (weatherDescription != null) {
            stmt.bindString(11, weatherDescription);
        }

        String weatherIcon = entity.getWeatherIcon();
        if (weatherIcon != null) {
            stmt.bindString(12, weatherIcon);
        }

        Double clouds = entity.getClouds();
        if (clouds != null) {
            stmt.bindDouble(13, clouds);
        }

        Double windSpeed = entity.getWindSpeed();
        if (windSpeed != null) {
            stmt.bindDouble(14, windSpeed);
        }

        Double rain = entity.getRain();
        if (rain != null) {
            stmt.bindDouble(15, rain);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public WeatherData readEntity(Cursor cursor, int offset) {
        WeatherData entity = new WeatherData( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // cityId
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // uniqueId
                cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // dt
                cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4), // temp
                cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // tempMin
                cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6), // tempMax
                cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7), // pressure
                cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8), // humidity
                cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // weather
                cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // weatherDescription
                cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // weatherIcon
                cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12), // clouds
                cursor.isNull(offset + 13) ? null : cursor.getDouble(offset + 13), // windSpeed
                cursor.isNull(offset + 14) ? null : cursor.getDouble(offset + 14) // rain
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, WeatherData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCityId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setUniqueId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDt(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setTemp(cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4));
        entity.setTempMin(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setTempMax(cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6));
        entity.setPressure(cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7));
        entity.setHumidity(cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8));
        entity.setWeather(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setWeatherDescription(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setWeatherIcon(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setClouds(cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12));
        entity.setWindSpeed(cursor.isNull(offset + 13) ? null : cursor.getDouble(offset + 13));
        entity.setRain(cursor.isNull(offset + 14) ? null : cursor.getDouble(offset + 14));
    }

    @Override
    protected final Long updateKeyAfterInsert(WeatherData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(WeatherData entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(WeatherData entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    /**
     * Internal query to resolve the "weatherDataList" to-many relationship of City.
     */
    public List<WeatherData> _queryCity_WeatherDataList(Long cityId) {
        synchronized (this) {
            if (city_WeatherDataListQuery == null) {
                QueryBuilder<WeatherData> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CityId.eq(null));
                city_WeatherDataListQuery = queryBuilder.build();
            }
        }
        Query<WeatherData> query = city_WeatherDataListQuery.forCurrentThread();
        query.setParameter(0, cityId);
        return query.list();
    }

}
