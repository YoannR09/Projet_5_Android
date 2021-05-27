package com.cleanup.todoc.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProjectTest {

    Project project = new Project(23,"name",1234);

    @Test
    public void getterAndSetterId() {
        // WHEN
        long id = project.getId();

        // THEN
        assertEquals(id, 23);
    }

    @Test
    public void getterAndSetterName() {
        // WHEN
        String name = project.getName();

        // THEN
        assertEquals(name, "name");
    }

    @Test
    public void getterAndSetterColor() {
        // WHEN
        int color = project.getColor();

        // THEN
        assertEquals(color,1234);
    }
}
