package com.example.myapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.myapplication.model.MyLog;

import java.util.List;


@Dao
public interface SimpleDao {
    @Query("SELECT * FROM MyLog")
    List<MyLog> getAllLogs();

    @Query("SELECT last_insert_rowid()")
    int getLastId();

    @Insert
    void insert(MyLog myLog);
    
    @Insert
    void insertAll(MyLog... myLogs);

    @Delete
    void delete(MyLog myLog);
}
