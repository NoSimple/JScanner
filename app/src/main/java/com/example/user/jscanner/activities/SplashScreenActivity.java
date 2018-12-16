package com.example.user.jscanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.jscanner.R;
import com.example.user.jscanner.presenters.SplashScreenPresenter;

public class SplashScreenActivity extends AppCompatActivity {

    private SplashScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        presenter = new SplashScreenPresenter();
        presenter.onAttach(this);
        //presenter.initDB();
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.onDetach();
        finish();
    }

    public void startNextActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
    }
}