package com.example.user.jscanner.presenters;

import com.example.user.jscanner.activities.DetailActivity;

public class BasePresenter {

    private DetailActivity activity;

    public void onAttach(DetailActivity activity) {
        this.activity = activity;
    }

    public void onDetach(){
        this.activity = null;
    }
}
