package com.example.lrjgreendaodemo.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.lrjgreendaodemo.Course;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COURSE".
*/
public class CourseDao extends AbstractDao<Course, Long> {

    public static final String TABLENAME = "COURSE";

    /**
     * Properties of entity Course.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Cno = new Property(0, Long.class, "cno", true, "_id");
        public final static Property Cname = new Property(1, String.class, "cname", false, "CNAME");
        public final static Property Ctime = new Property(2, int.class, "ctime", false, "CTIME");
        public final static Property Teacher = new Property(3, String.class, "teacher", false, "TEACHER");
    }


    public CourseDao(DaoConfig config) {
        super(config);
    }
    
    public CourseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COURSE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: cno
                "\"CNAME\" TEXT NOT NULL ," + // 1: cname
                "\"CTIME\" INTEGER NOT NULL ," + // 2: ctime
                "\"TEACHER\" TEXT NOT NULL );"); // 3: teacher
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COURSE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Course entity) {
        stmt.clearBindings();
 
        Long cno = entity.getCno();
        if (cno != null) {
            stmt.bindLong(1, cno);
        }
        stmt.bindString(2, entity.getCname());
        stmt.bindLong(3, entity.getCtime());
        stmt.bindString(4, entity.getTeacher());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Course entity) {
        stmt.clearBindings();
 
        Long cno = entity.getCno();
        if (cno != null) {
            stmt.bindLong(1, cno);
        }
        stmt.bindString(2, entity.getCname());
        stmt.bindLong(3, entity.getCtime());
        stmt.bindString(4, entity.getTeacher());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Course readEntity(Cursor cursor, int offset) {
        Course entity = new Course( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // cno
            cursor.getString(offset + 1), // cname
            cursor.getInt(offset + 2), // ctime
            cursor.getString(offset + 3) // teacher
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Course entity, int offset) {
        entity.setCno(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCname(cursor.getString(offset + 1));
        entity.setCtime(cursor.getInt(offset + 2));
        entity.setTeacher(cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Course entity, long rowId) {
        entity.setCno(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Course entity) {
        if(entity != null) {
            return entity.getCno();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Course entity) {
        return entity.getCno() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
