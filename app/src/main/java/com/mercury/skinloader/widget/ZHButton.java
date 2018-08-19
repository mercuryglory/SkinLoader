package com.mercury.skinloader.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.mercury.skinloader.R;
import com.mercury.skinloader.base.IDayNightView;

/**
 * @author Mercury
 * @descript
 * @since 2018/8/12
 */
public class ZHButton extends AppCompatButton implements IDayNightView{
    public ZHButton(Context context) {
        super(context);
    }

    public ZHButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZHButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void resetStyle(Resources.Theme theme) {
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{R.attr.textMainColor});
        int color = typedArray.getColor(0, 0);
        this.setTextColor(color);
        typedArray.recycle();
    }
}
