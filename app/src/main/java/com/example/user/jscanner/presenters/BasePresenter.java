package com.example.user.jscanner.presenters;

import com.example.user.jscanner.activities.DetailActivity;

import java.util.logging.Handler;

public class BasePresenter {

    private DetailActivity activity;
    private Handler handler;

    public void onAttach(DetailActivity activity) {
        this.activity = activity;
    }

    public void onDetach(){
        this.activity = null;
    }

    public void process(String code){
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
