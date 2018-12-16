package com.example.user.jscanner.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.user.jscanner.model.Country;


@Database(entities = {CountryItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase singleton;

    public static final String DATABASE_NAME = "countries.db";

    public abstract CountryDAO countryDAO();

    public static AppDatabase getAppDatabase(Context context) {
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

