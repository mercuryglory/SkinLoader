package com.mercury.skinloader.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mercury.skinloader.R;
import com.mercury.skinloader.base.BaseActivity;
import com.mercury.skinloader.event.ThemeChangeEvent;
import com.mercury.skinloader.fragment.DiscoverFragment;
import com.mercury.skinloader.fragment.HomeFragment;
import com.mercury.skinloader.fragment.MessageFragment;
import com.mercury.skinloader.fragment.MoreFragment;
import com.mercury.skinloader.fragment.NoticeFragment;
import com.mercury.skinloader.util.Const;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    ImageView    ivHome;
    ImageView    ivDiscover;
    ImageView    ivNotifiy;
    ImageView    ivMessage;
    ImageView    ivMore;
    LinearLayout llBottomBar;

    private Fragment mCurrentFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivHome = findViewById(R.id.iv_home);
        ivDiscover = findViewById(R.id.iv_discover);
        ivNotifiy = findViewById(R.id.iv_notifaction);
        ivMessage = findViewById(R.id.iv_message);
        ivMore = findViewById(R.id.iv_more);
        llBottomBar = findViewById(R.id.ll_bottom_bar);

        tintDrawableById(R.id.fl_home, true);
        tintDrawableById(R.id.fl_discover, false);
        tintDrawableById(R.id.fl_notice, false);
        tintDrawableById(R.id.fl_message, false);
        tintDrawableById(R.id.fl_more, false);

        findViewById(R.id.fl_home).setOnClickListener(this);
        findViewById(R.id.fl_discover).setOnClickListener(this);
        findViewById(R.id.fl_notice).setOnClickListener(this);
        findViewById(R.id.fl_message).setOnClickListener(this);
        findViewById(R.id.fl_more).setOnClickListener(this);

        EventBus.getDefault().register(this);

        switchFragment(FragmentType.HOME);

        SharedPreferences sharedPreferences = getSharedPreferences(Const.SP_NAME, MODE_PRIVATE);
        if (sharedPreferences != null) {
            int mode = sharedPreferences.getInt(Const.THEME_KEY, 1);
            if (mode == 1) {
                setTheme(R.style.Theme_Day);
            } else {
                setTheme(R.style.Theme_Night);
            }
        }

    }


    private void tintDrawableById(int viewId, boolean isSelected) {
        switch (viewId) {
            case R.id.fl_home:
                tintDrawables(ivHome, R.drawable.ic_bottomtabbar_feed, isSelected);
                break;
            case R.id.fl_discover:
                tintDrawables(ivDiscover, R.drawable.ic_bottomtabbar_discover, isSelected);
                break;
            case R.id.fl_notice:
                tintDrawables(ivNotifiy, R.drawable.ic_bottomtabbar_notification, isSelected);
                break;
            case R.id.fl_message:
                tintDrawables(ivMessage, R.drawable.ic_bottomtabbar_message, isSelected);
                break;
            case R.id.fl_more:
                tintDrawables(ivMore, R.drawable.ic_bottomtabbar_more, isSelected);
                break;
        }
    }

    @Subscribe
    public void receive(ThemeChangeEvent event) {

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void tintDrawables(@NonNull ImageView target, @DrawableRes int resId, boolean
            isSelected) {
        Drawable wrap = DrawableCompat.wrap(getResources().getDrawable(resId));
        if (isSelected) {
            wrap.setTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
        } else {
            wrap.setTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray)));
        }
        target.setBackground(wrap);

    }


    @Override
    public void onClick(View v) {
        onTabSelected(v.getId());

        switch (v.getId()) {
            case R.id.fl_home:
                switchFragment(FragmentType.HOME);
                break;
            case R.id.fl_discover:
                switchFragment(FragmentType.DISCOVER);
                break;
            case R.id.fl_notice:
                switchFragment(FragmentType.NOTIFY);
                break;
            case R.id.fl_message:
                switchFragment(FragmentType.MESSAGE);
                break;
            case R.id.fl_more:
                switchFragment(FragmentType.MORE);
                break;
            default:
                break;
        }
    }

    private void switchFragment(FragmentType type) {
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragmentByTag = manager.findFragmentByTag(getFragmentTag(type));

        if (mCurrentFrag != null && mCurrentFrag == fragmentByTag) {
            return;
        }

        FragmentTransaction transaction = manager.beginTransaction();
        if (mCurrentFrag != null) {
            transaction.hide(mCurrentFrag);
        }
        if (fragmentByTag == null) {
            fragmentByTag = createFragment(type);
            transaction.add(R.id.fl_container, fragmentByTag, getFragmentTag(type));
        } else {
            transaction.show(fragmentByTag);
        }

        transaction.commit();
        mCurrentFrag = fragmentByTag;

    }

    private String getFragmentTag(FragmentType type) {
        return "main" + type.ordinal();
    }

    private Fragment createFragment(FragmentType type) {
        switch (type) {
            case HOME:
                return new HomeFragment();
            case DISCOVER:
                return new DiscoverFragment();
            case NOTIFY:
                return new NoticeFragment();
            case MESSAGE:
                return new MessageFragment();
            case MORE:
                return new MoreFragment();
            default:
                throw new IllegalArgumentException("invalid fragment type");

        }
    }

    public enum FragmentType {
        HOME,
        DISCOVER,
        NOTIFY,
        MESSAGE,
        MORE
    }


    private void onTabSelected(int id) {
        int childCount = llBottomBar.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View target = llBottomBar.getChildAt(i);
            tintDrawableById(target.getId(), (target.getId() == id));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        EventBus.getDefault().unregister(this);

    }
}
