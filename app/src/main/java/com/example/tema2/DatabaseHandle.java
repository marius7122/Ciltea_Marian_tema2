package com.example.tema2;

import android.content.Context;

import androidx.room.Room;

public class DatabaseHandle {
    private Context context;
    private static DatabaseHandle instance;
    private AppDatabase appDatabase;

    private DatabaseHandle(Context context)
    {
        this.context=context;
        appDatabase= Room.databaseBuilder(context, AppDatabase.class,"MyReviews").build();
    }

    public  static  synchronized DatabaseHandle getInstance(Context context)
    {
        if(instance == null)
            instance = new DatabaseHandle(context);
        return instance;
    }

    public AppDatabase getAppDatabase()
    {
        return  appDatabase;
    }
}