package com.example.nvdovin.weatherapp.greendao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.example.nvdovin.weatherapp.model.Weather;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WEATHER".
*/
public class WeatherDao extends AbstractDao<Weather, Long> {

    public static final String TABLENAME = "WEATHER";

    /**
     * Properties of entity Weather.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property WeatherId = new Property(1, Integer.class, "weatherId", false, "WEATHER_ID");
        public final static Property Main = new Property(2, String.class, "main", false, "MAIN");
        public final static Property Description = new Property(3, String.class, "description", false, "DESCRIPTION");
        public final static Property Icon = new Property(4, String.class, "icon", false, "ICON");
    }

    private Query<Weather> weatherList_WeatherQuery;

    public WeatherDao(DaoConfig config) {
        super(config);
    }
    
    public WeatherDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WEATHER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"WEATHER_ID\" INTEGER," + // 1: weatherId
                "\"MAIN\" TEXT," + // 2: main
                "\"DESCRIPTION\" TEXT," + // 3: description
                "\"ICON\" TEXT);"); // 4: icon
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WEATHER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Weather entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer weatherId = entity.getWeatherId();
        if (weatherId != null) {
            stmt.bindLong(2, weatherId);
        }
 
        String main = entity.getMain();
        if (main != null) {
            stmt.bindString(3, main);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(4, description);
        }
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(5, icon);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Weather entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer weatherId = entity.getWeatherId();
        if (weatherId != null) {
            stmt.bindLong(2, weatherId);
        }
 
        String main = entity.getMain();
        if (main != null) {
            stmt.bindString(3, main);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(4, description);
        }
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(5, icon);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Weather readEntity(Cursor cursor, int offset) {
        Weather entity = new Weather( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // weatherId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // main
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // description
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // icon
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Weather entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setWeatherId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setMain(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDescription(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIcon(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Weather entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Weather entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Weather entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "weather" to-many relationship of WeatherList. */
    public List<Weather> _queryWeatherList_Weather(Long id) {
        synchronized (this) {
            if (weatherList_WeatherQuery == null) {
                QueryBuilder<Weather> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Id.eq(null));
                weatherList_WeatherQuery = queryBuilder.build();
            }
        }
        Query<Weather> query = weatherList_WeatherQuery.forCurrentThread();
        query.setParameter(0, id);
        return query.list();
    }

}
