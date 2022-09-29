package com.prateek.androidpr.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.prateek.androidpr.models.ListItem;

import java.util.List;

@Dao
public interface ItemsDao {

    @Query("SELECT text from items_table WHERE list_no = 1")
    LiveData<List<String>> getAllListOneItems();

    @Query("SELECT text from items_table WHERE list_no = 2")
    LiveData<List<String>> getAllListTwoItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertValue(ListItem item);

    @Query("DELETE FROM items_table WHERE list_no=1 AND text=:text")
    void deleteItemInListOne(String text);

    @Query("DELETE FROM items_table WHERE list_no=2 AND text=:text")
    void deleteItemInListTwo(String text);

    @Query("DELETE FROM items_table WHERE text = :text")
    void deleteFromBothTables(String text);

    @Query("UPDATE items_table SET list_no=2 WHERE text= :text AND list_no=1")
    void swapFromOneToTwo(String text);

    @Query("UPDATE items_table SET list_no=1 WHERE text= :text AND list_no=2")
    void swapFromTwoToOne(String text);


}
