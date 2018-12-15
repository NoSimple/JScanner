package com.example.user.jscanner.presenters;

import android.support.v7.app.AppCompatActivity;

public interface IBasePresenter {

    void onAttach(AppCompatActivity activity);
    void onDetach();
}