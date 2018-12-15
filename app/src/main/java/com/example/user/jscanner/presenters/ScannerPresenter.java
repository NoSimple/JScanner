package com.example.user.jscanner.presenters;

import android.support.v7.app.AppCompatActivity;

import com.example.user.jscanner.activities.ScannerActivity;

public class ScannerPresenter implements IBasePresenter {

    private ScannerActivity activity;

    @Override
    public void onAttach(AppCompatActivity activity) {
        this.activity = (ScannerActivity) activity;
    }

    @Override
    public void onDetach() {
        activity = null;
    }
}