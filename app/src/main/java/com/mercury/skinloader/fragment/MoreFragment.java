package com.mercury.skinloader.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.mercury.skinloader.R;
import com.mercury.skinloader.activity.SecondActivity;
import com.mercury.skinloader.event.ThemeChangeEvent;
import com.mercury.skinloader.util.ThemeManager;
import com.mercury.skinloader.widget.ZHTextView;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Mercury
 * @descript
 * @since 2018/8/8
 */
public class MoreFragment extends Fragment implements CompoundButton.OnCheckedChangeListener,
        View.OnClickListener {

    Switch     mSwitch;
    Button     mButton;
    ZHTextView mZHTextView;

    RelativeLayout mRelativeLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        mSwitch = view.findViewById(R.id.switchbar);
        mButton = view.findViewById(R.id.button);
        mZHTextView = view.findViewById(R.id.zh_text);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSwitch.setOnCheckedChangeListener(this);
        mButton.setOnClickListener(this);

        if (getContext() != null) {
            mSwitch.setChecked(ThemeManager.getCurrentTheme(getContext()) == 2);
            mSwitch.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        EventBus.getDefault().post(new ThemeChangeEvent());
        if (getContext() != null) {
            ThemeManager.switchTheme(getContext(), isChecked);
        }

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), SecondActivity.class));

    }
}
