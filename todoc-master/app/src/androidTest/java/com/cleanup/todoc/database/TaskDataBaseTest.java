package com.cleanup.todoc.database;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.entity.TaskEntity;
import com.cleanup.todoc.util.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class TaskDataBaseTest{

    // FOR DATA
    private ToDocDataBase database;

    private final int TASK_ID = 99;

    private final TaskEntity TASK_DEMO = new TaskEntity(TASK_ID, 1,"name", new Date().getTime());

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ToDocDataBase.class)
                .allowMainThreadQueries()
                .addCallback(prepopulateDatabase())
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGet() throws InterruptedException {
        this.database.taskDao().insertTask(TASK_DEMO);
        TaskEntity task = LiveDataTestUtil.getValue(this.database.taskDao().getTask(TASK_ID));
        assertTrue(task.getName().equals(TASK_DEMO.getName()));
        assertTrue(task.getId() == TASK_DEMO.getId());
    }

    @Test
    public void insertAndUpdate() throws InterruptedException {
        this.database.taskDao().insertTask(TASK_DEMO);
        TaskEntity task = LiveDataTestUtil.getValue(this.database.taskDao().getTask(TASK_ID));
        assertTrue(task.getName().equals(TASK_DEMO.getName()));
        task.setName("update");
        this.database.taskDao().updateTask(task);
        TaskEntity taskUpdate = LiveDataTestUtil.getValue(this.database.taskDao().getTask(TASK_ID));
        assertTrue(taskUpdate.getName().equals("update"));
    }

    @Test
    public void insertAndDelete() throws InterruptedException {
        this.database.taskDao().insertTask(TASK_DEMO);
        TaskEntity task = LiveDataTestUtil.getValue(this.database.taskDao().getTask(TASK_ID));
        assertTrue(task.getName().equals(TASK_DEMO.getName()));
        this.database.taskDao().deleteTask(TASK_ID);

        //TEST
        TaskEntity taskDelete = LiveDataTestUtil.getValue(this.database.taskDao().getTask(TASK_ID));
        assertNull(taskDelete);
    }



    private static RoomDatabase.Callback prepopulateDatabase(){
        return new RoomDatabase.Callback() {

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
            }
        };
    }
}
