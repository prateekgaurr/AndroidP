package com.prateek.androidpr.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items_table")
public class ListItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "list_no")
    public int listNo;


    public ListItem(String text, int listNo) {
        this.text = text;
        this.listNo = listNo;
    }
}
