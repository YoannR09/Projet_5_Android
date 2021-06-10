package com.cleanup.todoc.mappers;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.viewModel.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class TaskModelToTaskViewModelMapper implements Mapper<Task, TaskViewModel> {

    @Override
    public TaskViewModel map(Task in) {
        if(in != null) {
            TaskViewModel taskAdd = new TaskViewModel(
                    in.getId(),
                    in.getProjectId(),
                    in.getName(),
                    in.getCreationTimestamp());
            return taskAdd;
        }
        return null;
    }

    @Override
    public List<TaskViewModel> maps(List<Task> ins) {
        List<TaskViewModel> tasksViewModel = new ArrayList<>();
        for(Task task: ins) {
            TaskViewModel taskAdd = new TaskViewModel(
                    task.getId(),
                    task.getProjectId(),
                    task.getName(),
                    task.getCreationTimestamp());
            tasksViewModel.add(taskAdd);
        }
        return tasksViewModel;
    }
}
