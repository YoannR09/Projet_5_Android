package com.cleanup.todoc.viewModel;

import com.cleanup.todoc.model.Project;

import java.util.Comparator;

public class TaskViewModel {

    private long id;
    private long taskProjectId;
    private String taskName;
    private long time;
    private Project project;

    /**
     * The timestamp when the task has been created
     */
    private long creationTimestamp;

    public TaskViewModel(long id, long taskProjectId, String taskName, long time) {
        this.id = id;
        this.taskProjectId = taskProjectId;
        this.taskName = taskName;
        this.time = time;
        this.setCreationTimestamp(time);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTaskProjectId() {
        return taskProjectId;
    }

    public void setTaskProjectId(long taskProjectId) {
        this.taskProjectId = taskProjectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Comparator to sort task from A to Z
     */
    public static class TaskAZComparator implements Comparator<TaskViewModel> {
        @Override
        public int compare(TaskViewModel left, TaskViewModel right) {
            return left.taskName.compareToIgnoreCase(right.taskName);
        }
    }

    /**
     * Comparator to sort task from Z to A
     */
    public static class TaskZAComparator implements Comparator<TaskViewModel> {
        @Override
        public int compare(TaskViewModel left, TaskViewModel right) {
            return right.taskName.compareToIgnoreCase(left.taskName);
        }
    }

    /**
     * Comparator to sort task from last created to first created
     */
    public static class TaskRecentComparator implements Comparator<TaskViewModel> {
        @Override
        public int compare(TaskViewModel left, TaskViewModel right) {
            return (int) (right.creationTimestamp - left.creationTimestamp);
        }
    }

    /**
     * Comparator to sort task from first created to last created
     */
    public static class TaskOldComparator implements Comparator<TaskViewModel> {
        @Override
        public int compare(TaskViewModel left, TaskViewModel right) {
            return (int) (left.creationTimestamp - right.creationTimestamp);
        }
    }

    /**
     * Sets the timestamp when the task has been created.
     *
     * @param creationTimestamp the timestamp when the task has been created to set
     */
    private void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }
}
