package com.mercury.skinloader.base;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.ViewGroup;

import com.mercury.skinloader.R;

import java.util.ArrayList;

/**
 * @author Mercury
 * @descript
 * @since 2018/8/12
 */
public class BaseActivity extends AppCompatActivity {

    public static ArrayList<BaseActivity> sActivityStack = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sActivityStack.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sActivityStack.remove(this);
    }

    public void switchTheme(int themeMode) {
        View decorView = this.getWindow().getDecorView();
        Bitmap bitmap = this.obtainCachedBitmap(decorView);
        if (decorView instanceof ViewGroup && bitmap != null) {
            View view = new View(this);
            view.setBackground(new BitmapDrawable(getResources(), bitmap));
            ((ViewGroup) decorView).addView(view, new ViewGroup.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            switchThemeInternal(themeMode);
            view.animate().alpha(0.0f).setDuration(300L).start();
        }


    }

    private void switchThemeInternal(int themeMode) {
        AppCompatDelegate.setDefaultNightMode(themeMode);
        reTheme(themeMode);
        recolorBackground();
        recolorStatusbar();
        this.change(this.getWindow().getDecorView());
    }

    private void reTheme(int themeMode) {
        if (themeMode==2) {
            setTheme(R.style.Theme_Night);
        } else{
            setTheme(R.style.Theme_Day);
        }
    }

    private void change(View view) {
        if (view instanceof IDayNightView) {
            try {
                ((IDayNightView) view).resetStyle(getTheme());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                change(((ViewGroup) view).getChildAt(i));
            }
        }
    }

    private void recolorStatusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TypedArray typedArray = getTheme().obtainStyledAttributes(new int[]{R.attr
                    .statusbarMainColor});
            int color = typedArray.getColor(0, 0);
            typedArray.recycle();

            this.getWindow().setStatusBarColor(color);
        }
    }

    private void recolorBackground() {
        TypedArray typedArray = getTheme().obtainStyledAttributes(new int[]{R.attr
                .backgroundMainColor});
        int color = typedArray.getColor(0, 0);
        typedArray.recycle();
        this.getWindow().setBackgroundDrawable(new ColorDrawable(color));
    }

    private Bitmap obtainCachedBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache != null) {
            Bitmap bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
            drawingCache.recycle();
            return bitmap;
        } else {
            return null;
        }
    }
}
