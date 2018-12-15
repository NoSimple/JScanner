package com.example.user.jscanner.room;

import androidx.room.Dao;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface CountryDAO {

    @Query("SELECT * FROM CountryDB")
    Single<CountryDB> getSingle();

}
