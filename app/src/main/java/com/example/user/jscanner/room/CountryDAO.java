package com.example.user.jscanner.room;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface CountryDAO {

    @Query("SELECT * FROM countries")
    List<CountryItem> getSingle();

    @Query("SELECT * FROM countries WHERE startwith = :id")
    CountryItem findByStartwith(String id);

}
