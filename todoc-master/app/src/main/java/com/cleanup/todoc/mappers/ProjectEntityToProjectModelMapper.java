package com.cleanup.todoc.mappers;

import com.cleanup.todoc.entity.ProjectEntity;
import com.cleanup.todoc.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectEntityToProjectModelMapper implements Mapper<ProjectEntity, Project> {
    @Override
    public Project map(ProjectEntity in) {
        return new Project(in.getId(), in.getName(), in.getColor());
    }

    @Override
    public List<Project> maps(List<ProjectEntity> ins) {
        List<Project> arrayProjects = new ArrayList<>();
        for(ProjectEntity in: ins) {
            arrayProjects.add(new Project(in.getId(), in.getName(), in.getColor()));
        }
        return arrayProjects;
    }
}
