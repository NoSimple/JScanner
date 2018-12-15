package com.example.user.jscanner.presenters;

import android.support.v7.app.AppCompatActivity;

import com.example.user.jscanner.activities.DetailActivity;

import java.util.logging.Handler;

public class DetailPresenter implements IBasePresenter {

    private DetailActivity activity;
    private Handler handler;

    @Override
    public void onAttach(AppCompatActivity activity) {
        this.activity = (DetailActivity) activity;
    }

    @Override
    public void onDetach() {
        activity = null;
    }

    public void process(String code) {
        activity.showPB();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.hidePB();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}