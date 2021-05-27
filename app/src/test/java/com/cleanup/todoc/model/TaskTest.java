package com.cleanup.todoc.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TaskTest {

    private final long ID = 33;
    private final long PROJECT_ID = 3;
    private final String NAME = "NAME";
    private final long TIME = new Date().getTime();


    Task task = new Task(ID,PROJECT_ID,NAME,TIME);

    @Test
    public void getterAndSetterId() {
        // WHEN
        double id = task.getId();

        // THEN
        assertEquals(ID, id, 0.0);
        assertNotEquals(0, id, 0.0);
    }

    @Test
    public void getterAndSetterProject() {
        // GIVEN
        task.setProject(new Project(12,"name",1234));
        // WHEN
        Project project = task.getProject();
        // THEN
        assertEquals(12, project.getId());
        assertNotEquals(0, project.getId());
        assertEquals("name", project.getName());
        assertEquals(1234, project.getColor());
    }

    @Test
    public void getterAndSetterName () {
        // WHEN
        String name = task.getName();
        // THEN
        assertEquals(name, NAME);
        assertNotEquals(name, "falsename");
    }

    @Test
    public void getterAndSetterTime() {
        // WHEN
        long time = task.getCreationTimestamp();
        // THEN
        assertEquals(time, TIME);
        assertNotEquals(time, 124152);
    }

}
