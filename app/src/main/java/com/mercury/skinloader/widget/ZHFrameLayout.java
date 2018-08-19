package com.mercury.skinloader.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.mercury.skinloader.R;
import com.mercury.skinloader.base.IDayNightView;

/**
 * @author Mercury
 * @descript
 * @since 2018/8/12
 */
public class ZHFrameLayout extends FrameLayout implements IDayNightView{

    public ZHFrameLayout(@NonNull Context context) {
        super(context);
    }

    public ZHFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZHFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void resetStyle(Resources.Theme theme) {
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{R.attr.backgroundMainColor});
        int color = typedArray.getColor(0, 0);
        this.setBackgroundColor(color);
        typedArray.recycle();
    }
}
