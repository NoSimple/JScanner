package com.example.user.jscanner.room;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CountryDAO {

    @Query("SELECT * FROM CountryDB")
    CountryDB getSingle();

}
