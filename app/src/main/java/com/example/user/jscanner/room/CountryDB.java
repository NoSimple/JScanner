package com.example.user.jscanner.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "countryDB")
public class CountryDB {

    public CountryDB(@android.support.annotation.NonNull String startwith, String country, String countrycode) {
        this.startwith = startwith;
        this.country = country;
        this.countrycode = countrycode;
    }

    @android.support.annotation.NonNull
    @PrimaryKey
    @ColumnInfo(name = "startwith")
    private String startwith;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "countrycode")
    private String countrycode;

    public String getStartwith() {
        return startwith;
    }

    public String getCountry() {
        return country;
    }

    public String getCountrycode() {
        return countrycode;
    }


}

