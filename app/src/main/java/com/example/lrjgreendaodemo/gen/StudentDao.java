package com.example.lrjgreendaodemo.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.lrjgreendaodemo.Student;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "STUDENT".
*/
public class StudentDao extends AbstractDao<Student, Long> {

    public static final String TABLENAME = "STUDENT";

    /**
     * Properties of entity Student.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Sno = new Property(0, long.class, "sno", true, "_id");
        public final static Property Sname = new Property(1, String.class, "sname", false, "SNAME");
        public final static Property Ssex = new Property(2, String.class, "ssex", false, "SSEX");
        public final static Property Sage = new Property(3, int.class, "sage", false, "SAGE");
    }


    public StudentDao(DaoConfig config) {
        super(config);
    }
    
    public StudentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"STUDENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: sno
                "\"SNAME\" TEXT NOT NULL ," + // 1: sname
                "\"SSEX\" TEXT NOT NULL ," + // 2: ssex
                "\"SAGE\" INTEGER NOT NULL );"); // 3: sage
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"STUDENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Student entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getSno());
        stmt.bindString(2, entity.getSname());
        stmt.bindString(3, entity.getSsex());
        stmt.bindLong(4, entity.getSage());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Student entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getSno());
        stmt.bindString(2, entity.getSname());
        stmt.bindString(3, entity.getSsex());
        stmt.bindLong(4, entity.getSage());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Student readEntity(Cursor cursor, int offset) {
        Student entity = new Student( //
            cursor.getLong(offset + 0), // sno
            cursor.getString(offset + 1), // sname
            cursor.getString(offset + 2), // ssex
            cursor.getInt(offset + 3) // sage
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Student entity, int offset) {
        entity.setSno(cursor.getLong(offset + 0));
        entity.setSname(cursor.getString(offset + 1));
        entity.setSsex(cursor.getString(offset + 2));
        entity.setSage(cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Student entity, long rowId) {
        entity.setSno(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Student entity) {
        if(entity != null) {
            return entity.getSno();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Student entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
