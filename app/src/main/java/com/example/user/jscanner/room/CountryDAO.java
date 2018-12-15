package com.example.user.jscanner.room;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface CountryDAO {

    @Query("SELECT * FROM country")
    List<CountryItem> getSingle();

//    @Query("SELECT * FROM country WHERE startwith = :id")
//    CountryItem findById(int id);
}
