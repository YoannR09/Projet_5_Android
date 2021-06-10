package com.cleanup.todoc.repo;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.util.LiveDataTestUtil;
import com.cleanup.todoc.database.ToDocDataBase;
import com.cleanup.todoc.entity.TaskEntity;
import com.cleanup.todoc.factory.Repositories;
import com.cleanup.todoc.factory.TaskRepository;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TaskRepositoryTest {

    // FOR DATA
    private ToDocDataBase database;

    private final int TASK_ID = 99;

    private final TaskEntity TASK_DEMO = new TaskEntity(TASK_ID, 1,"name", new Date().getTime());

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        ToDocDataBase.createInstanceTest(InstrumentationRegistry.getInstrumentation().getTargetContext());
        this.database = ToDocDataBase.getInstance();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    private TaskRepository getTaskRepository() {
        return Repositories.getTaskRepository();
    }


    @Test
    public void insertAndGet() throws InterruptedException {
        getTaskRepository().insertTask(TASK_DEMO);
        Task task = LiveDataTestUtil.getValue(getTaskRepository().getTask(TASK_ID));
        assertTrue(task.getName().equals(TASK_DEMO.getName()));
        assertTrue(task.getId() == TASK_DEMO.getId());
    }


    @Test
    public void insertAndDelete() throws InterruptedException {
        getTaskRepository().insertTask(TASK_DEMO);
        Task task = LiveDataTestUtil.getValue(getTaskRepository().getTask(TASK_ID));
        assertTrue(task.getName().equals(TASK_DEMO.getName()));
        getTaskRepository().deleteTask(TASK_ID);

        //TEST
        Task taskDelete = LiveDataTestUtil.getValue(getTaskRepository().getTask(TASK_ID));
        assertNull(taskDelete);
    }

    @Test
    public void insertAndUpdate() throws InterruptedException {
        getTaskRepository().insertTask(TASK_DEMO);
        Task task = LiveDataTestUtil.getValue(getTaskRepository().getTask(TASK_ID));
        assertTrue(task.getName().equals(TASK_DEMO.getName()));
        task.setName("update");
        TaskEntity taskUpdate = new TaskEntity(task.getId(),task.getProjectId(), "update", task.getCreationTimestamp());
        getTaskRepository().updateTask(taskUpdate);
        Task taskUpdated = LiveDataTestUtil.getValue(getTaskRepository().getTask(TASK_ID));
        assertTrue(taskUpdated.getName().equals("update"));
    }



    public static RoomDatabase.Callback prepopulateDatabase(){
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
