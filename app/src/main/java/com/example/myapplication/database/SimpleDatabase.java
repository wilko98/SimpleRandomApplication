package com.example.myapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.myapplication.model.MyLog;


@Database(entities = {MyLog.class},version=1)
public abstract class SimpleDatabase extends RoomDatabase {
    public abstract SimpleDao getSimpleDao();

}
