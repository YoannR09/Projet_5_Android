package com.cleanup.todoc.entity;



import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ProjectEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    private int color;

    public ProjectEntity() {}

    public ProjectEntity(long id, String name, int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
