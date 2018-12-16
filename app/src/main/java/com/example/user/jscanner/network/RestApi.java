package com.example.user.jscanner.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    private static final String URL = "http://daracul.000webhostapp.com/index.php/";

    private static final int TIMEOUT_IN_SECONDS = 30;
    private static RestApi sRestApi;

    private final CountryEndpoint barcodesEndpoint;


    public static synchronized RestApi getInstance() {
        if (sRestApi == null) {
            sRestApi = new RestApi();
        }
        return sRestApi;
    }


    private RestApi() {
        final Retrofit retrofit = buildRetrofitClient();
        barcodesEndpoint = retrofit.create(CountryEndpoint.class);
    }

    @NonNull
    private Retrofit buildRetrofitClient() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .client(buildOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private OkHttpClient buildOkHttpClient() {
        final HttpLoggingInterceptor networkLogInterceptor = new HttpLoggingInterceptor();
        networkLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);


        return new OkHttpClient.Builder()
                .addInterceptor(networkLogInterceptor)
                .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .build();
    }

    public CountryEndpoint countriesEndpoint() {
        return barcodesEndpoint;
    }

}
