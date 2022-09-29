package com.prateek.androidpr.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.prateek.androidpr.models.ListItem;

@Database(entities = {ListItem.class}, version = 1)
public abstract class RoomDB extends RoomDatabase{
    private static RoomDB instance;

    public static RoomDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    RoomDB.class,
                    "lists_db")
                    .build();
        }
        return instance;
    }

    public abstract ItemsDao dao();
}
