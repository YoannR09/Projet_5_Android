package com.cleanup.todoc.database;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.entity.ProjectEntity;
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
public class ProjectDataBaseTest {

    // FOR DATA
    private ToDocDataBase database;

    private final int PROJECT_ID = 99;

    private final ProjectEntity PROJECT_DEMO = new ProjectEntity(PROJECT_ID, "name",12345);

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

    @Test
    public void insertAndGet() throws InterruptedException {
        this.database.projectDao().insertProject(PROJECT_DEMO);
        ProjectEntity projectEntity = LiveDataTestUtil.getValue(this.database.projectDao().getProject(PROJECT_ID));
        assertTrue(projectEntity.getName().equals(PROJECT_DEMO.getName()));
        assertTrue(projectEntity.getId() == PROJECT_DEMO.getId());
    }

    @Test
    public void insertAndUpdate() throws InterruptedException {
        this.database.projectDao().insertProject(PROJECT_DEMO);
        ProjectEntity projectEntity = LiveDataTestUtil.getValue(this.database.projectDao().getProject(PROJECT_ID));
        assertTrue(projectEntity.getName().equals(PROJECT_DEMO.getName()));
        projectEntity.setName("update");
        this.database.projectDao().updateProject(projectEntity);
        ProjectEntity projectUpdate = LiveDataTestUtil.getValue(this.database.projectDao().getProject(PROJECT_ID));
        assertTrue(projectUpdate.getName().equals("update"));
    }

    @Test
    public void insertAndDelete() throws InterruptedException {
        this.database.projectDao().insertProject(PROJECT_DEMO);
        ProjectEntity projectEntity = LiveDataTestUtil.getValue(this.database.projectDao().getProject(PROJECT_ID));
        assertTrue(projectEntity.getName().equals(PROJECT_DEMO.getName()));
        this.database.projectDao().deleteProject(PROJECT_ID);

        //TEST
        ProjectEntity projectDelete = LiveDataTestUtil.getValue(this.database.projectDao().getProject(PROJECT_ID));
        assertNull(projectDelete);
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
