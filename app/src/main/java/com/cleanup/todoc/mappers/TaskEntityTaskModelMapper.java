package com.cleanup.todoc.mappers;

import com.cleanup.todoc.entity.TaskEntity;
import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskEntityTaskModelMapper{

    public TaskEntityTaskModelMapper() {
    }


    public Task map(TaskEntity t) {
        if(t != null) {
            return new Task(t.getId(),
                    t.getProjectId(),
                    t.getName(),
                    t.getCreationTimestamp());
        }
        return null;
    }


    public List<Task> maps(List<TaskEntity> ins) {
        List<Task> arrayToTask = new ArrayList<>();
        for(TaskEntity pair: ins) {
            arrayToTask.add(map(pair));
        }
        return arrayToTask;
    }
}
