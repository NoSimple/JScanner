package com.example.user.jscanner.room;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

@Dao
public interface CountryDAO {

    @Query("SELECT * FROM CountryDB")
    CountryDB getSingle();

}
