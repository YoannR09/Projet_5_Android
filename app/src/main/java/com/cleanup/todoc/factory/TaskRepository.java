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

    public LiveData<List<Task>> getProjectsByTaskId(long taskId) {
        return mappersTask(dao.taskDao().getProjectsByTaskId(taskId));
    }

    public long insertTask(TaskEntity taskEntity) {
        return dao.taskDao().insertTask(taskEntity);
    }

    public int updateTask(TaskEntity taskEntity) {
        return dao.taskDao().updateTask(taskEntity);
    }

    public int deleteTask(long taskId) {
        return dao.taskDao().deleteTask(taskId);
    }

    public LiveData<Task> mapperTask(LiveData<TaskEntity> taskEntity) {
        LiveData<Task> taskModel = Transformations.map(taskEntity, task -> {
            return new TaskEntityTaskModelMapper().map(task);
        });
        return taskModel;
    }

    public LiveData<List<Task>> mappersTask(LiveData<List<TaskEntity>> tasksEntity) {
        LiveData<List<Task>> taskModel = Transformations.map(tasksEntity, tasks -> {
            List<TaskEntity> taskList = new ArrayList<>();
            for(TaskEntity task: tasks) {
                taskList.add(task);
            }
            return new TaskEntityTaskModelMapper().maps(taskList);
        });
        return taskModel;
    }
}
