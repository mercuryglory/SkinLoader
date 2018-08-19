package com.mercury.skinloader.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.mercury.skinloader.base.IDayNightView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Mercury
 * @descript
 * @since 2018/8/16
 */
public class ZHRecyclerView extends RecyclerView implements IDayNightView {

    public ZHRecyclerView(Context context) {
        super(context);
    }

    public ZHRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZHRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void resetStyle(Resources.Theme theme) {
        try {
            Field mRecycler = RecyclerView.class.getDeclaredField("mRecycler");
            mRecycler.setAccessible(true);
            Method clear = Recycler.class.getDeclaredMethod("clear");
            clear.setAccessible(true);
            clear.invoke(mRecycler.get(this));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        this.getRecycledViewPool().clear();


    }
}
