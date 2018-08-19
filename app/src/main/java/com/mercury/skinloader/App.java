package com.mercury.skinloader;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;

import com.mercury.skinloader.util.Const;

/**
 * @author Mercury
 * @descript
 * @since 2018/8/12
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences sharedPreferences = getSharedPreferences(Const.SP_NAME, MODE_PRIVATE);
        if (sharedPreferences != null) {
            int mode = sharedPreferences.getInt(Const.THEME_KEY, 1);
            AppCompatDelegate.setDefaultNightMode(mode);
        }
    }
}
