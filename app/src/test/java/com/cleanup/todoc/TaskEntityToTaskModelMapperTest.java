package com.cleanup.todoc;

import com.cleanup.todoc.entity.TaskEntity;
import com.cleanup.todoc.mappers.TaskEntityTaskModelMapper;
import com.cleanup.todoc.model.Task;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TaskEntityToTaskModelMapperTest {

    TaskEntityTaskModelMapper testMapper = new TaskEntityTaskModelMapper();

    @Test
    public void map() {
        // GIVEN
        TaskEntity t = new TaskEntity(2,22,"test",33);

        // WHEN
        Task result = testMapper.map(t);

        // THEN
        assertNotNull(result);
        assertEquals(result.getId(), 2);
        assertEquals(result.getName(), "test");
        assertEquals(result.getCreationTimestamp(), 33);
        assertEquals(result.getProjectId(), 22);
    }

    @Test
    public void maps() {
        // GIVEN
        TaskEntity t = new TaskEntity(2,22,"test",33);
        TaskEntity t2 = new TaskEntity(4,4,"test2",11);
        List<TaskEntity> vList = new ArrayList<>();
        vList.add(t);vList.add(t2);

        // WHEN
        List<Task> results = testMapper.maps(vList);

        // THEN
        assertEquals(results.size(), 2);
        assertEquals(results.get(0).getId(), 2);
        assertEquals(results.get(0).getName(), "test");
        assertEquals(results.get(0).getCreationTimestamp(), 33);
        assertEquals(results.get(0).getProjectId(), 22);
        assertEquals(results.get(1).getId(), 4);
        assertEquals(results.get(1).getName(), "test2");
        assertEquals(results.get(1).getCreationTimestamp(), 11);
        assertEquals(results.get(1).getProjectId(), 4);
    }
}
