package com.cleanup.todoc.viewModel;

import com.cleanup.todoc.model.Project;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class TaskViewModelTest {

    private final long ID = 33;
    private final long PROJECT_ID = 3;
    private final String NAME = "NAME";
    private final long TIME = new Date().getTime();


    TaskViewModel taskViewModel = new TaskViewModel(0,0,"",0);

    @Test
    public void getterAndSetterId() {
        // GIVEN
        taskViewModel.setId(ID);

        // WHEN
        double id = taskViewModel.getId();

        // THEN
        assertEquals(33, id, 0.0);
        assertNotEquals(0, id, 0.0);
    }

    @Test
    public void getterAndSetterProjectId() {
        // GIVEN
        taskViewModel.setTaskProjectId(PROJECT_ID);
        // WHEN
        long projectId = taskViewModel.getTaskProjectId();
        // THEN
        assertEquals(3, projectId);
        assertNotEquals(0, projectId);
    }

    @Test
    public void getterAndSetterName () {
        // GIVEN
        taskViewModel.setTaskName(NAME);
        // WHEN
        String name = taskViewModel.getTaskName();
        // THEN
        assertEquals(name, NAME);
        assertNotEquals(name, "");
    }

    @Test
    public void getterAndSetterTime() {
        // GIVEN
        taskViewModel.setTime(TIME);
        // WHEN
        long time = taskViewModel.getTime();
        // THEN
        assertEquals(time, TIME);
        assertNotEquals(time, 0);
    }

    @Test
    public void getterAndSetterProject() {
        // GIVEN
        taskViewModel.setProject(new Project(1,"name",2));
        // WHEN
        Project project = taskViewModel.getProject();
        // THEN
        assertNotNull(project);
        assertEquals(project.getId(), 1);
    }
}
