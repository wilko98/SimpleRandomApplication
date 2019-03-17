package com.example.myapplication;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;

import com.example.myapplication.database.SimpleDatabase;
import com.example.myapplication.database.Storage;

public class ApplicationSingleton extends Application {

    private Storage mStorage;
    private SharedPreferences mSharedPreferences;
    @Override
    public void onCreate() {
        final SimpleDatabase database = Room.databaseBuilder(this,SimpleDatabase.class,"simple_database")
                .allowMainThreadQueries()
                .build();
        mStorage = new Storage(database.getSimpleDao());
        mSharedPreferences = getSharedPreferences(BuildConfig.PREFS_KEY, MODE_PRIVATE);
        super.onCreate();
    }

    public Storage getmStorage() {
        return mStorage;
    }

    public SharedPreferences getmSharedPreferences() {
        return mSharedPreferences;
    }
}
