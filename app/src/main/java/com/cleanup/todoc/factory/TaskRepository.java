package com.cleanup.todoc.factory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.cleanup.todoc.database.ToDocDataBase;
import com.cleanup.todoc.entity.TaskEntity;
import com.cleanup.todoc.mappers.TaskEntityTaskModelMapper;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;


public class TaskRepository {

    ToDocDataBase dao;

    public TaskRepository(ToDocDataBase dao) {
        this.dao = dao;
    }

    public LiveData<List<Task>> getTasks() {
        return mappersTask(dao.taskDao().getTasks());
    }

    public LiveData<Task> getTask(long id) {
        return mapperTask(dao.taskDao().getTask(id));
    }

    public void insertTask(TaskEntity taskEntity) {
        dao.taskDao().insertTask(taskEntity);
    }

    public void updateTask(TaskEntity taskEntity) {
        dao.taskDao().updateTask(taskEntity);
    }

    public void deleteTask(long taskId) {
        dao.taskDao().deleteTask(taskId);
    }

    public LiveData<Task> mapperTask(LiveData<TaskEntity> taskEntity) {
        return Transformations.map(taskEntity, task -> new TaskEntityTaskModelMapper().map(task));
    }

    public LiveData<List<Task>> mappersTask(LiveData<List<TaskEntity>> tasksEntity) {
        return Transformations.map(tasksEntity, tasks -> {
            List<TaskEntity> taskList = new ArrayList<>(tasks);
            return new TaskEntityTaskModelMapper().maps(taskList);
        });
    }
}
