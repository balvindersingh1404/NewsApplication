package com.example.balvinder.newsapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by balvinder on 18/12/17.
 */

public class NewsApp extends Application {
    private SharedPreferences sharedPreferences;
    private static NewsApp newsAPP;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
    }


    public static NewsApp getInstance() {
        if (newsAPP == null) {
            newsAPP = (NewsApp) context;
        }
        return newsAPP;
    }
    public SharedPreferences    getSharedPreferences() {
        return sharedPreferences;
    }
}
