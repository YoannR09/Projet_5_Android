package com.cleanup.todoc;

import com.cleanup.todoc.entity.ProjectEntity;
import com.cleanup.todoc.mappers.ProjectEntityToProjectModelMapper;
import com.cleanup.todoc.model.Project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProjectEntityToProjectMapperTest {

    ProjectEntityToProjectModelMapper mapper = new ProjectEntityToProjectModelMapper();

    @Test
    public void map() {
        // GIVEN
        ProjectEntity projectEntity = new ProjectEntity(12,"name",2314122);

        // WHEN
        Project project = mapper.map(projectEntity);

        // THEN
        assertNotNull(project);
        assertEquals(project.getId(), 12);
        assertEquals(project.getName(), "name");
        assertEquals(project.getColor(), 2314122);
    }

}
