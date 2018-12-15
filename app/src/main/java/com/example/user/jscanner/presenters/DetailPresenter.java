package com.example.user.jscanner.presenters;

import android.support.v7.app.AppCompatActivity;

import com.example.user.jscanner.activities.DetailActivity;

public class DetailPresenter implements IBasePresenter {

    private DetailActivity activity;

    @Override
    public void onAttach(AppCompatActivity activity) {
        this.activity = (DetailActivity) activity;
    }

    @Override
    public void onDetach() {
        activity = null;
    }
}