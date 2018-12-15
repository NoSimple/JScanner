package com.example.user.jscanner.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;



public class Country {

    private String startWtih;
    private String country;
    private String countryCode;

    public Country(){}

    public Country(String startWith, String country, String countryCode){ //TESTING CONSTRUCTOR
        this.startWtih = startWith;
        this.country = country;
        this.countryCode = countryCode;
    }

    public String getStartWtih() {
        return startWtih;
    }

    public void setStartWtih(String startWtih) {
        this.startWtih = startWtih;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


}

