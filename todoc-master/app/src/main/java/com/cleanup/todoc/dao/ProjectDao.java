package com.cleanup.todoc.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc.entity.ProjectEntity;
import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM ProjectEntity")
    LiveData<List<ProjectEntity>> getProjects();

    @Query("SELECT * FROM ProjectEntity WHERE id = :id")
    LiveData<ProjectEntity> getProject(long id);

    @Insert
    long insertProject(ProjectEntity projectEntity);

    @Update
    int updateProject(ProjectEntity projectEntity);

    @Query("DELETE FROM ProjectEntity WHERE id = :projectId")
    int deleteProject(long projectId);

}
