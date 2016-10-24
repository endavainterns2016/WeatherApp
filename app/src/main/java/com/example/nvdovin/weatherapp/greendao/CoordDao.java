package com.example.nvdovin.weatherapp.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.nvdovin.weatherapp.model.Coord;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COORD".
*/
public class CoordDao extends AbstractDao<Coord, Long> {

    public static final String TABLENAME = "COORD";

    /**
     * Properties of entity Coord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Lon = new Property(1, Double.class, "lon", false, "LON");
        public final static Property Lat = new Property(2, Double.class, "lat", false, "LAT");
    }


    public CoordDao(DaoConfig config) {
        super(config);
    }
    
    public CoordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"LON\" REAL," + // 1: lon
                "\"LAT\" REAL);"); // 2: lat
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Coord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Double lon = entity.getLon();
        if (lon != null) {
            stmt.bindDouble(2, lon);
        }
 
        Double lat = entity.getLat();
        if (lat != null) {
            stmt.bindDouble(3, lat);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Coord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Double lon = entity.getLon();
        if (lon != null) {
            stmt.bindDouble(2, lon);
        }
 
        Double lat = entity.getLat();
        if (lat != null) {
            stmt.bindDouble(3, lat);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Coord readEntity(Cursor cursor, int offset) {
        Coord entity = new Coord( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getDouble(offset + 1), // lon
            cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2) // lat
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Coord entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLon(cursor.isNull(offset + 1) ? null : cursor.getDouble(offset + 1));
        entity.setLat(cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Coord entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Coord entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Coord entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
