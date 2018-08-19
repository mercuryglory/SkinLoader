package com.mercury.skinloader.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.mercury.skinloader.R;
import com.mercury.skinloader.base.IDayNightView;

/**
 * @author Mercury
 * @descript
 * @since 2018/8/12
 */
public class ZHImageView extends AppCompatImageView implements IDayNightView{
    public ZHImageView(Context context) {
        super(context);
    }

    public ZHImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZHImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void resetStyle(Resources.Theme theme) {
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{R.attr.drawableRes});
        Drawable drawable = typedArray.getDrawable(0);
        typedArray.recycle();
        this.setImageDrawable(drawable);
    }
}
