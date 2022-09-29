package com.prateek.androidp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.prateek.androidp.models.ListItemTableOne;
import com.prateek.androidp.models.ListItemTableTwo;

@Database(entities = {ListItemTableOne.class, ListItemTableTwo.class}, version = 1)
public abstract class ListDatabase extends RoomDatabase {
    public abstract ListDao dao();
    public static ListDatabase INSTANCE;

    public static ListDatabase getInstance(Context application){
        if(INSTANCE == null)

            INSTANCE = Room.databaseBuilder(
                            application.getApplicationContext(),
                            ListDatabase.class, "list_db")
                    .build();

        return INSTANCE;
    }


}
