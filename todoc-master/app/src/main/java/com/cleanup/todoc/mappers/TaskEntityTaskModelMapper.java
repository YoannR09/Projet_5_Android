package com.cleanup.todoc.mappers;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.cleanup.todoc.entity.TaskEntity;
import com.cleanup.todoc.factory.ProjectRepository;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class TaskEntityTaskModelMapper implements Mapper<TaskEntity, Task>{

    private ProjectRepository repository;
    private List<Project> projectsList = new ArrayList<>();


    public TaskEntityTaskModelMapper(ProjectRepository projectRepository) {
        this.repository = projectRepository;
        this.repository.getProjects().observeForever(projects -> {
            projectsList = projects;
        });

    }

    @Override
    public Task map(TaskEntity in) {
        Task taskAdd = new Task(in.getId(),
                in.getProjectId(),
                in.getName(),
                in.getCreationTimestamp());
        taskAdd.setProject(projectsList.get((int) in.getProjectId()));
        return taskAdd;
    }

    @Override
    public List<Task> maps(List<TaskEntity> ins) {
        List<Task> arrayToTask = new ArrayList<>();
        for(TaskEntity task: ins) {

            Task taskAdd = new Task(task.getId(),
                    task.getProjectId(),
                    task.getName(),
                    task.getCreationTimestamp());
            taskAdd.setProject(Project.getProjectById(task.getProjectId()));

            arrayToTask.add(taskAdd);
        }
        return arrayToTask;
    }
}
