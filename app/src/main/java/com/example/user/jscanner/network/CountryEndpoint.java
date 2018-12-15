package com.example.user.jscanner.network;

import android.support.annotation.NonNull;

import com.example.user.jscanner.model.Country;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CountryEndpoint {
    @GET("csv-to-api")
    Single<List<Country>> countryObject(@Query("source") @NonNull String source);
}
