package com.cleanup.todoc.factory;

import android.content.Context;

import com.cleanup.todoc.database.ToDocDataBase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repositories {

    private static ProjectRepository projectRepository;
    private static TaskRepository taskRepository;

    public static TaskRepository getTaskRepository() {
        if(taskRepository == null) taskRepository = Repositories.createTaskRepository();
        return taskRepository;
    }

    public static ProjectRepository getProjectRepository() {
        if(projectRepository == null) projectRepository = Repositories.createProjectRepository();
        return projectRepository;
    }


    public static TaskRepository createTaskRepository() {
        return new TaskRepository(ToDocDataBase.getInstance());
    }

    public static ProjectRepository createProjectRepository() {
       return new ProjectRepository(ToDocDataBase.getInstance());
    }

    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }


}
