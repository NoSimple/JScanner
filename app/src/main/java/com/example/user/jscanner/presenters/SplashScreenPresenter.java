package com.example.user.jscanner.presenters;

import android.support.v7.app.AppCompatActivity;

import com.example.user.jscanner.activities.SplashScreenActivity;

public class SplashScreenPresenter implements IBasePresenter {

    private SplashScreenActivity activity;

    @Override
    public void onAttach(AppCompatActivity activity) {
        this.activity = (SplashScreenActivity) activity;
    }

    @Override
    public void onDetach() {
        activity = null;
    }

    public void initDB() {
        activity.startNextActivity();
    }
}
