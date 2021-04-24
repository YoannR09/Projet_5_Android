package com.cleanup.todoc.database;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.entity.ProjectEntity;
import com.cleanup.todoc.entity.TaskEntity;
import com.cleanup.todoc.model.Project;

@Database(entities = {TaskEntity.class, ProjectEntity.class}, version = 1, exportSchema = false)
public abstract class ToDocDataBase extends RoomDatabase {

    // --- SINGLETON ---
    private static ToDocDataBase INSTANCE;

    // --- DAO ---
    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();

    public static void createInstance(Context context) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ToDocDataBase.class, "ToDoc.db")
                            .addCallback(prepopulateDatabase())
                            .build();
    }

    // --- INSTANCE ---
    public static ToDocDataBase getInstance() {
        return INSTANCE;
    }


    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentProject1 = new ContentValues();
                contentProject1.put("name", "Projet Tartampion");
                contentProject1.put("color", 0xFFEADAD1);
                db.insert("ProjectEntity", SQLiteDatabase.CONFLICT_ABORT, contentProject1);

                ContentValues contentProject2 = new ContentValues();
                contentProject2.put("name", "Projet Lucidia");
                contentProject2.put("color", 0xFFB4CDBA);
                db.insert("ProjectEntity", SQLiteDatabase.CONFLICT_ABORT, contentProject2);

                ContentValues contentProject3 = new ContentValues();
                contentProject3.put("name", "Projet Circus");
                contentProject3.put("color", 0xFFA3CED2);
                db.insert("ProjectEntity", SQLiteDatabase.CONFLICT_ABORT, contentProject3);

                ContentValues contentValues = new ContentValues();
                contentValues.put("projectId", 1);
                contentValues.put("name", "test name");
                contentValues.put("creationTimestamp", 1234);
                db.insert("TaskEntity", SQLiteDatabase.CONFLICT_ABORT, contentValues);
            }
        };
    }
}
