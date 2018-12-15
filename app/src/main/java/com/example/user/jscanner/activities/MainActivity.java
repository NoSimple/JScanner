package com.example.user.jscanner.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.jscanner.R;
import com.example.user.jscanner.model.Country;
import com.example.user.jscanner.network.RestApi;
import com.example.user.jscanner.room.AppDatabase;
import com.example.user.jscanner.room.CountryItem;
import com.example.user.jscanner.room.CountryRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SHARED_PREF_INSTANCE = "INTRO_SHARED_PREF";
    private static final String BOOLEAN_PREF_KEY = "KEY_SHARED_PREF";
    public static final String SOURCE_URL = "http://daracul.000webhostapp.com/countries.csv";
    private static final String LOG_TAG = "Testlog" ;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    Button scanBtn;
    EditText manualET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "isFirst? "+isFirstTime());
        if (isFirstTime()){
            loadDataFromNetwork();
            //TODO load data to DB
            saveBooleanToSharedPref(false);
        } {
            new CountryRepository(MainActivity.this).getItems()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<CountryItem>>() {
                        @Override
                        public void accept(List<CountryItem> countryItems) throws Exception {
                            Log.d(LOG_TAG,"from db "+countryItems.get(0).getCountry());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d(LOG_TAG,"ERRROR");
                        }
                    });
        }

        scanBtn = findViewById(R.id.scan_btn);
        manualET = findViewById(R.id.manual_barcode);

        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String manualBarcode = manualET.getText().toString();
        if (manualBarcode.isEmpty()){
            //TODO открыть камеру
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("CODE", manualBarcode);
            startActivity(intent);
        }
    }

    private boolean isFirstTime() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_INSTANCE, MODE_PRIVATE);
        return sharedPreferences.getBoolean(BOOLEAN_PREF_KEY, true);
    }

    private void saveBooleanToSharedPref(Boolean firstTime) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_INSTANCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(BOOLEAN_PREF_KEY, firstTime);
        editor.apply();
    }

    private void loadDataFromNetwork(){
        Disposable disposable = RestApi.getInstance().countriesEndpoint().countryObject(SOURCE_URL).
                subscribeOn(Schedulers.io())
                .map(new Function<List<Country>, List<CountryItem>>() {
                    @Override
                    public List<CountryItem> apply(List<Country> countries) {
                        List<CountryItem> countryItemList = new ArrayList<>();
                        for (Country country:countries){
                            countryItemList.add(new CountryItem(
                                    country.getStartWtih(),
                                    country.getCountry(),
                                    country.getCountryCode()));
                        }
                        return countryItemList;
                    }
                }).flatMap(new Function<List<CountryItem>, SingleSource<? extends List<CountryItem>>>() {
                    @Override
                    public SingleSource<? extends List<CountryItem>> apply(List<CountryItem> countryItems) {
                        return new CountryRepository(MainActivity.this).saveNews(countryItems);

                    }
                }).subscribe(new Consumer<List<CountryItem>>() {
                    @Override
                    public void accept(List<CountryItem> countryItems) throws Exception {
                        Log.d(LOG_TAG,"Accepted");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(LOG_TAG,throwable.getClass().getSimpleName());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
