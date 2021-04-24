package com.cleanup.todoc.factory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.cleanup.todoc.dao.ProjectDao;
import com.cleanup.todoc.entity.ProjectEntity;
import com.cleanup.todoc.mappers.ProjectEntityToProjectModelMapper;
import com.cleanup.todoc.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

    ProjectDao dao;

    public ProjectRepository(ProjectDao dao) {
        this.dao = dao;
    }

    public LiveData<List<Project>> getProjects() {
        return mappersProject(dao.getProjects());
    }

    public LiveData<Project> getProject(long id) {
        return mapperProject(dao.getProject(id));
    }

    public long insertProject(ProjectEntity projectEntity) {
        return dao.insertProject(projectEntity);
    }

    public int updateProject(ProjectEntity projectEntity) {
        return dao.updateProject(projectEntity);
    }

    public int deleteProject(long projectId) {
        return dao.deleteProject(projectId);
    }

    public LiveData<Project> mapperProject(LiveData<ProjectEntity> projectEntity) {
        LiveData<Project> projectModel = Transformations.map(projectEntity, project -> {
            return new ProjectEntityToProjectModelMapper().map(project);
        });
        return projectModel;
    }

    public LiveData<List<Project>> mappersProject(LiveData<List<ProjectEntity>> projectsEntity) {
        LiveData<List<Project>> projectsModel = Transformations.map(projectsEntity, projects -> {
            return new ProjectEntityToProjectModelMapper().maps(projects);
        });
        return projectsModel;
    }
}
