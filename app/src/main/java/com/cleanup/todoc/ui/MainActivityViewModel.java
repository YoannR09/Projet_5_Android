package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.entity.TaskEntity;
import com.cleanup.todoc.factory.Repositories;
import com.cleanup.todoc.factory.TaskRepository;
import com.cleanup.todoc.mappers.TaskModelToTaskViewModelMapper;
import com.cleanup.todoc.viewModel.TaskViewModel;

import java.util.List;
import java.util.concurrent.Executor;

public class MainActivityViewModel extends ViewModel {

    private TaskRepository taskRepository;

    // REPOSITORIES
    private final Executor executor;

    public MainActivityViewModel(TaskRepository taskRepository, Executor executor) {
        this.taskRepository = taskRepository;
        this.executor = executor;
    }

    /**
     * Return task view model list mapped
     */
    public LiveData<List<TaskViewModel>> getTasks() {
        return Transformations.map(taskRepository.getTasks(), tasks -> new TaskModelToTaskViewModelMapper().maps(tasks));
    }

    /**
     * Define Project for each TaskViewModel
     */
    public void initProjectForEach(List<TaskViewModel> viewModelList) {
        for(TaskViewModel taskView: viewModelList) {
            taskView.setProject(Repositories.getProjectRepository()
                    .getProjectByIdOnCurrentList((int) taskView.getTaskProjectId()));
        }
    }


    public void addTask(TaskEntity task) {
        executor.execute(() -> taskRepository.insertTask(task));
    }

    public void deleteTask(long taskId) {
        executor.execute(() -> taskRepository.deleteTask(taskId));
    }


}
