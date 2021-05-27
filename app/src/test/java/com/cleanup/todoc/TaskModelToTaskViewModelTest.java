package com.cleanup.todoc;

import com.cleanup.todoc.mappers.TaskModelToTaskViewModelMapper;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.viewModel.TaskViewModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaskModelToTaskViewModelTest {

    TaskModelToTaskViewModelMapper mapper = new TaskModelToTaskViewModelMapper();

    @Test
    public void map() {
        // GIVEN
        Task task1 = new Task(23, 12,"name1",123);

        // WHEN
        TaskViewModel taskViewModel = mapper.map(task1);

        // THEN
        assertEquals(taskViewModel.getId(), 23);
        assertEquals(taskViewModel.getTaskName(), "name1");
        assertEquals(taskViewModel.getTaskProjectId(), 12);
        assertEquals(taskViewModel.getTime(), 123);
    }

    @Test
    public void maps() {
        // GIVEN
        Task task1 = new Task(23, 12,"name1",123);
        Task task2 = new Task(56, 2,"name2",323);
        List<Task> taskList = new ArrayList();
        taskList.add(task1);
        taskList.add(task2);

        // WHEN
        List<TaskViewModel> viewModelList = mapper.maps(taskList);

        // THEN
        assertEquals(viewModelList.size(), 2);
        assertEquals(viewModelList.get(0).getId(), 23);
        assertEquals(viewModelList.get(0).getTaskName(), "name1");
        assertEquals(viewModelList.get(0).getTaskProjectId(), 12);
        assertEquals(viewModelList.get(0).getTime(), 123);
        assertEquals(viewModelList.get(1).getId(), 56);
        assertEquals(viewModelList.get(1).getTaskName(), "name2");
        assertEquals(viewModelList.get(1).getTaskProjectId(), 2);
        assertEquals(viewModelList.get(1).getTime(), 323);
    }
}
