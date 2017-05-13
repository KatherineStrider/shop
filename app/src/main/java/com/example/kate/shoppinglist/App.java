package com.example.kate.shoppinglist;

import android.app.Application;
import android.util.Log;

import com.example.kate.shoppinglist.SQLite.DBShopList;

/**
 * Created by Kate on 12.05.2017.
 */

public class App extends Application {

    private static DBShopList dbShopList = null;
    public static final String LOG_TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {

        super.onCreate();

        dbShopList = new DBShopList(getApplicationContext());
    }

    public static DBShopList getDB() {
        return dbShopList;
    }

    public static void Log(String log) {
        Log.d(LOG_TAG, log);
    }
}
