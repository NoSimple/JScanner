package com.example.user.jscanner.room;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;

public class CountryRepository {

    public final Context mContext;

    public CountryRepository(Context mContext) {
        this.mContext = mContext;
    }

    public Single<List<CountryItem>> getItems() {
        return Single.fromCallable(new Callable<List<CountryItem>>() {
            @Override
            public List<CountryItem> call() throws Exception {
                AppDatabase db = AppDatabase.getAppDatabase(mContext);

                return db.countryDAO().getSingle();
            }
        });
    }

    public Single<CountryItem> getItemByID(final String id) {
        return Single.fromCallable(new Callable<CountryItem>() {
            @Override
            public CountryItem call() throws Exception {
                AppDatabase db = AppDatabase.getAppDatabase(mContext);

                return db.countryDAO().findByStartwith(id);
            }
        });
    }
}
