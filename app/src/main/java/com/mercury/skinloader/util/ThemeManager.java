package com.mercury.skinloader.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.mercury.skinloader.base.BaseActivity;

import java.util.Iterator;

/**
 * @author Mercury
 * @descript
 * @since 2018/8/12
 */
public class ThemeManager {

    public static void switchTheme(Context context, boolean isNight) {
        int themeMode = isNight ? 2 : 1;
        if (themeMode == getCurrentTheme(context)) {
            return;
        }

        SharedPreferences sp = context.getSharedPreferences(Const.SP_NAME, Context
                .MODE_PRIVATE);
        sp.edit().putInt(Const.THEME_KEY, themeMode).apply();

        Iterator<BaseActivity> iterator = BaseActivity.sActivityStack.iterator();
        while (iterator.hasNext()) {
            (iterator.next()).switchTheme(themeMode);
        }
    }

    public static int getCurrentTheme(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Const
                .SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Const.THEME_KEY, 1);
    }
}
