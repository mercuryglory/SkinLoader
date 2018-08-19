package com.mercury.skinloader.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.mercury.skinloader.R;
import com.mercury.skinloader.base.IDayNightView;

/**
 * @author Mercury
 * @descript
 * @since 2018/8/12
 */
public class ZHTextView extends AppCompatTextView implements IDayNightView {

    private int attr_textColor = -1;

    public ZHTextView(Context context) {
        super(context);
    }

    public ZHTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_textColor = getAttributeValue(attrs, android.R.attr.textColor);
    }

    public ZHTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_textColor = getAttributeValue(attrs, android.R.attr.textColor);
    }

    public int getAttributeValue(AttributeSet attr, int paramInt) {
        int value = -1;
        int count = attr.getAttributeCount();
        for (int i = 0; i < count; i++) {
            if (attr.getAttributeNameResource(i) == paramInt) {
                String str = attr.getAttributeValue(i);
                if (str != null) {
                    if (str.startsWith("?")) {
                        value = Integer.parseInt(str.substring(1, str.length()));
                        return value;
                    } else if (str.startsWith("@")) {
                        value = attr.getAttributeResourceValue(i, -1);
                        return value;
                    }

                }
            }
        }
        return value;

    }

    @Override
    public void resetStyle(Resources.Theme theme) {
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{R.attr.textMainColor});
        int color = typedArray.getColor(0, 0);
        this.setTextColor(color);
        typedArray.recycle();
    }
}
