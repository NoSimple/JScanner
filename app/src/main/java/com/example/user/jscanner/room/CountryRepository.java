package com.example.user.jscanner.room;

import android.content.Context;

import java.util.concurrent.Callable;

import io.reactivex.Single;

public class CountryRepository {

    public final Context mContext;

    public CountryRepository(Context mContext) {
        this.mContext = mContext;
    }

    public Single<CountryDB> getData() {
        return Single.fromCallable(new Callable<CountryDB>() {
            @Override
            public CountryDB call() throws Exception {
                AppDatabase db = AppDatabase.getAppDatabase(mContext);

                return db.countryDAO().getSingle();
            }
        });
    }

}
