package com.example.user.jscanner.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "countryDB")
public class CountryDB {

    public CountryDB(@NonNull String startwith, String country, String countrycode) {
        this.startwith = startwith;
        this.country = country;
        this.countrycode = countrycode;
    }

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

