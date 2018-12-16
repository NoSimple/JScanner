package com.example.user.jscanner.presenters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.jscanner.R;
import com.example.user.jscanner.activities.DetailActivity;
import com.example.user.jscanner.room.CountryItem;
import com.example.user.jscanner.room.CountryRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter implements IBasePresenter {

    private static final String COUNTRY_FLAGS_IO = "https://www.countryflags.io/%s/flat/64.png";
    private DetailActivity activity;
    private CountryRepository db;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    final private RequestOptions imageOption = new RequestOptions()
            .placeholder(R.drawable.flag_placeholder)
            .fallback(R.drawable.flag_placeholder)
            .centerCrop();
    private RequestManager imageLoader;



    @Override
    public void onAttach(AppCompatActivity activity) {
        this.activity = (DetailActivity) activity;
        this.imageLoader = Glide.with(activity).applyDefaultRequestOptions(imageOption);

        db = new CountryRepository(activity.getApplicationContext());
    }

    @Override
    public void onDetach() {
        activity = null;
        if (db != null) {
            db = null;
        }
        compositeDisposable.clear();
        imageLoader = null;
    }

    public void process(final String code) {
        activity.showPB();
        Disposable disposable = db.getItemByID(code.substring(0, 3)).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CountryItem>() {
                    @Override
                    public void accept(CountryItem countryItem) throws Exception {
                        fillData(countryItem, code);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("myLogs", throwable.getClass().getSimpleName());
                        fillData(null, code);
                    }
                });
        compositeDisposable.add(disposable);
            }

    private void fillData(CountryItem country, String code) {
        Bitmap bitmap = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(code, BarcodeFormat.EAN_13, 1024, 512);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Bitmap finalBitmap = bitmap;
        if (country != null && bitmap != null) {
            activity.setFlagText(country.getCountry());
            Resources resources = activity.getResources();
            final int resourceId = resources.getIdentifier(country.getCountrycode() + ".png", "drawable",
                    activity.getPackageName());
            if (resourceId != 0) {
                activity.setFlagImage(resources.getDrawable(resourceId, activity.getTheme()));
            } else {
                imageLoader
                        .load(String.format(COUNTRY_FLAGS_IO,country.getCountrycode()))
                        .into(activity.getFlagImageView());
            }
            activity.setBarcodeImage(finalBitmap);
            activity.setBarcodeCode(code);
        } else {
            activity.setFlagText("Error!");
            activity.hideBarcodeImage();
            activity.hideSearch();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.hideLoadPB();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        activity.hidePB();

    }
}

