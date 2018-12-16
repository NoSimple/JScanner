package com.example.user.jscanner.presenters;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.user.jscanner.activities.SplashScreenActivity;
import com.example.user.jscanner.model.Country;
import com.example.user.jscanner.model.CountryMapper;
import com.example.user.jscanner.network.RestApi;
import com.example.user.jscanner.room.AppDatabase;
import com.example.user.jscanner.room.CountryItem;
import com.example.user.jscanner.room.CountryRepository;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SplashScreenPresenter implements IBasePresenter {
    public static final String SOURCE_URL = "http://daracul.000webhostapp.com/countries.csv";
    private static final String LOG_TAG = "Testlog";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private CountryRepository db;


    private SplashScreenActivity activity;

    @Override
    public void onAttach(AppCompatActivity activity) {
        this.activity = (SplashScreenActivity) activity;
        db = new CountryRepository(activity.getApplicationContext());
    }

    @Override
    public void onDetach() {
        activity = null;
        if (db != null) {
            db = null;
        }
        compositeDisposable.clear();
    }

    public void initDB() {
        checkDatabase();
        Log.d(LOG_TAG,"Base exists? " + doesDatabaseExist());
    }

    private void checkDatabase() {
        Disposable disposable = db.getItems().subscribeOn(Schedulers.io())
                .delay(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<List<CountryItem>>() {
                    @Override
                    public void accept(List<CountryItem> countryItems) throws Exception {
                        if (countryItems.isEmpty()) {loadDataFromNetwork();} else {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    activity.startNextActivity();
                                }
                            });

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(LOG_TAG,"ERROR WITH CHECKING DB");
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activity.showAlert();
                            }
                        });
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void loadDataFromNetwork() {
        Disposable disposable = RestApi.getInstance().countriesEndpoint().countryObject(SOURCE_URL).
                subscribeOn(Schedulers.io())
                .map(new Function<List<Country>, List<CountryItem>>() {
                    @Override
                    public List<CountryItem> apply(List<Country> countries) {
                        return CountryMapper.convertToDbItem(countries);
                    }
                }).flatMap(new Function<List<CountryItem>, SingleSource<? extends List<CountryItem>>>() {
                    @Override
                    public SingleSource<? extends List<CountryItem>> apply(List<CountryItem> countryItems) {
                        return db.saveNews(countryItems);

                    }
                })
                .subscribe(new Consumer<List<CountryItem>>() {
                    @Override
                    public void accept(List<CountryItem> countryItems) throws Exception {
                        activity.startNextActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(LOG_TAG, throwable.getClass().getSimpleName());
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activity.showAlert();
                            }
                        });
                    }
                });
        compositeDisposable.add(disposable);
    }

    private  boolean doesDatabaseExist() {
        File dbFile = activity.getDatabasePath(AppDatabase.DATABASE_NAME);
        return dbFile.exists();
    }




}
