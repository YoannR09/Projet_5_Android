package com.cleanup.todoc.factory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.cleanup.todoc.dao.TaskDao;
import com.cleanup.todoc.entity.TaskEntity;
import com.cleanup.todoc.mappers.TaskEntityTaskModelMapper;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskRepository {

    TaskDao dao;

    public TaskRepository(TaskDao dao) {
        this.dao = dao;
    }

    public LiveData<List<Task>> getTasks() {
        return mappersTask(dao.getTasks());
    }

    public LiveData<Task> getTask(long id) {
        return mapperTask(dao.getTask(id));
    }

    public LiveData<List<Task>> getProjectsByTaskId(long taskId) {
        return mappersTask(dao.getProjectsByTaskId(taskId));
    }

    public long insertTask(TaskEntity taskEntity) {
        return dao.insertTask(taskEntity);
    }

    public int updateTask(TaskEntity taskEntity) {
        return dao.updateTask(taskEntity);
    }

    public int deleteTask(long taskId) {
        return dao.deleteTask(taskId);
    }

    public LiveData<Task> mapperTask(LiveData<TaskEntity> taskEntity) {
        LiveData<Task> taskModel = Transformations.map(taskEntity, task -> {
            return new TaskEntityTaskModelMapper(Repositories.getProjectRepository()).map(task);
        });
        return taskModel;
    }

    public LiveData<List<Task>> mappersTask(LiveData<List<TaskEntity>> tasksEntity) {
        LiveData<List<Task>> taskModel = Transformations.map(tasksEntity, tasks -> {
            return new TaskEntityTaskModelMapper(Repositories.getProjectRepository()).maps(tasks);
        });
        return taskModel;
    }
}
