package com.prateek.androidp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.prateek.androidp.models.ListItemTableOne;
import com.prateek.androidp.models.ListItemTableTwo;

import java.util.List;

@androidx.room.Dao
public interface ListDao {

    @Query("SELECT * FROM table_one ORDER BY id DESC")
    LiveData<List<ListItemTableOne>> getAllListOneItems();

    @Query("SELECT * FROM table_two ORDER BY id DESC")
    LiveData<List<ListItemTableTwo>> getAllListTwoItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInTableOne(ListItemTableOne item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInTableTwo(ListItemTableTwo item);

    @Query("DELETE FROM table_one WHERE id=:id")
    void deleteItemFromTableOne(int id);

    @Query("DELETE FROM table_two WHERE id=:id")
    void deleteItemFromTableTwo(int id);

    @Query("DELETE FROM table_one WHERE text=:text")
    void deleteItemFromTableOne(String text);

    @Query("DELETE FROM table_two WHERE text=:text")
    void deleteItemFromTableTwo(String text);


}
