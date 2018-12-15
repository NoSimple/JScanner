package com.example.user.jscanner.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.user.jscanner.R;
import com.example.user.jscanner.presenters.DetailPresenter;

public class DetailActivity extends AppCompatActivity {

    public static final String SOURCE_URL = "http://daracul.000webhostapp.com/countries.csv";
    private static final String LOG_TAG = "Testlog" ;

    private ProgressBar progressBar;
    private DetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        progressBar = findViewById(R.id.det_pb);

        presenter = new DetailPresenter();
        presenter.onAttach(this);

        /*
        Disposable disposable = RestApi.getInstance().countriesEndpoint().countryObject(SOURCE_URL).
                subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<List<Country>>() {
                    @Override
                    public void accept(List<Country> countries) throws Exception {
                        for (Country country: countries){
                            Log.d(LOG_TAG, country.getStartWtih()+" " +country.getCountry());
                        }

                    }
                }, new io.reactivex.functions.Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(LOG_TAG, throwable.getClass().getSimpleName()+" ");
                    }
                });
        */
        presenter.process(getIntent().getStringExtra("CODE"));
    }

    @Override
    protected void onStop() {
        presenter.onDetach();
        super.onStop();
    }

    public void showPB(){
       progressBar.setVisibility(View.VISIBLE);
    }

    public void hidePB(){
        progressBar.setVisibility(View.GONE);
    }

}
