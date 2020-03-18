package com.example.tema2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Review {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "FullName")
    public String fullName;

    @ColumnInfo(name = "Mark")
    public String mark;

    public Review(String fullName, String mark) {
        this.fullName = fullName;
        this.mark = mark;
    }
}
