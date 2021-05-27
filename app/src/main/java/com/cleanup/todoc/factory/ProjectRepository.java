package com.cleanup.todoc.factory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.cleanup.todoc.database.ToDocDataBase;
import com.cleanup.todoc.entity.ProjectEntity;
import com.cleanup.todoc.mappers.ProjectEntityToProjectModelMapper;
import com.cleanup.todoc.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

   ToDocDataBase dao;
   public static List<Project> currentList = new ArrayList<>();

    public ProjectRepository(ToDocDataBase dao) {
        this.dao = dao;
        mappersProject(dao.projectDao().getProjects()).observeForever((obs) -> {
            currentList = obs;
        });
    }

    public LiveData<List<Project>> getProjects() {
        return mappersProject(dao.projectDao().getProjects());
    }

    public LiveData<Project> getProject(long id) {
        return mapperProject(dao.projectDao().getProject(id));
    }

    public long insertProject(ProjectEntity projectEntity) {
        return dao.projectDao().insertProject(projectEntity);
    }

    public int updateProject(ProjectEntity projectEntity) {
        return dao.projectDao().updateProject(projectEntity);
    }

    public int deleteProject(long projectId) {
        return dao.projectDao().deleteProject(projectId);
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

    /**
     * Return Project with project id
     * Find this on current Project list
     * @param projectId
     * @return
     */
    public Project getProjectByIdOnCurrentList(int projectId) {
        for (Project project : currentList) {
            if (project.getId() == projectId)
                return project;
        }
        return null;
    }

    public static List<Project> getCurrentList() {
        return currentList;
    }
}
