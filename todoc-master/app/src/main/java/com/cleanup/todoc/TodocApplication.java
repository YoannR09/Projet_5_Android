package com.cleanup.todoc;

import android.app.Application;

import com.cleanup.todoc.database.ToDocDataBase;

public class TodocApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToDocDataBase.createInstance(this);
    }
}
