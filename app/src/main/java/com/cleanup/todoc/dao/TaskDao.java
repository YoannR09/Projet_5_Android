package com.cleanup.todoc.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc.entity.TaskEntity;
import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM TaskEntity")
    LiveData<List<TaskEntity>> getTasks();

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    LiveData<TaskEntity> getTask(long id);

    @Query("SELECT * FROM TaskEntity WHERE projectId = :taskId")
    LiveData<List<TaskEntity>> getProjectsByTaskId(long taskId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTask(TaskEntity taskEntity);

    @Update
    int updateTask(TaskEntity taskEntity);

    @Query("DELETE FROM TaskEntity WHERE id = :taskId")
    int deleteTask(long taskId);

}
