package com.prateek.androidp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_two")

public class ListItemTableTwo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "text")
    public String text;

    public ListItemTableTwo(String text) {
        this.text = text;
    }
}
