package com.example.user.jscanner.room;

import android.content.Context;

import com.example.user.jscanner.model.Country;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private AppDatabase singleton;

    private static final String DATABASE_NAME = "NewsItemsDatabase.db";

    public abstract CountryDAO newsItemDAO();

    public AppDatabase getAppDatabase(Context context) {
        if (singleton == null) {
            synchronized (AppDatabase.class) {
                if (singleton == null) {
                    singleton = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            DATABASE_NAME)
                            .build();
                }
            }
        }
        return singleton;
    }
}

