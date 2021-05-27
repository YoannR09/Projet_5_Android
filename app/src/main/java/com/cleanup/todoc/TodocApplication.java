package com.cleanup.todoc;

import android.app.Application;

import com.cleanup.todoc.database.ToDocDataBase;
import com.cleanup.todoc.factory.Repositories;

public class TodocApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToDocDataBase.createInstance(this);
        Repositories.createProjectRepository();
        Repositories.createTaskRepository();
    }
}
