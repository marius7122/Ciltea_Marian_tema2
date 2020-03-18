package com.example.tema2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Review.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReviewDao reviewDao();
}