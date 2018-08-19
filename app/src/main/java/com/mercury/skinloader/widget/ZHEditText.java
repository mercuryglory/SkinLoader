package com.mercury.skinloader.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mercury.skinloader.R;
import com.mercury.skinloader.base.IDayNightView;

import java.lang.reflect.Field;

/**
 * @author Mercury
 * @descript
 * @since 2018/8/12
 */
public class ZHEditText extends AppCompatEditText implements IDayNightView{
    public ZHEditText(Context context) {
        super(context);
    }

    public ZHEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZHEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void resetStyle(Resources.Theme theme) {
        resetTextColor(theme);
        resetEditAttr(theme);
        resetBackground(theme);
    }

    private void resetBackground(Resources.Theme theme) {
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{R.attr.editbgRes});
        Drawable drawable = typedArray.getDrawable(0);
        this.setBackground(drawable);
        typedArray.recycle();
    }

    private void resetEditAttr(Resources.Theme theme) {
        try {
            TypedArray typedArray = theme.obtainStyledAttributes(new int[]{R.attr.textCursorRes});
            Drawable drawable = typedArray.getDrawable(0);
            typedArray.recycle();

            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(this);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);
            Drawable[] drawables = new Drawable[]{drawable};
            fCursorDrawable.set(editor, drawables);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        setCursorVisible(true);
    }

    private void resetTextColor(Resources.Theme theme) {
        TypedArray typedArray = theme.obtainStyledAttributes(new int[]{R.attr.textMainColor});
        int color = typedArray.getColor(0, 0);
        this.setTextColor(color);
        typedArray.recycle();
    }
}
