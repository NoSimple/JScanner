package com.example.user.jscanner.model;

import android.support.annotation.NonNull;

import com.example.user.jscanner.room.CountryItem;

import java.util.ArrayList;
import java.util.List;

public class CountryMapper {
    @NonNull
    public static List<CountryItem> convertToDbItem(List<Country> countries) {
        List<CountryItem> countryItemList = new ArrayList<>();
        for (Country country:countries){
            countryItemList.add(new CountryItem(
                    country.getStartWtih(),
                    country.getCountry(),
                    country.getCountryCode()));
        }
        return countryItemList;
    }
}
