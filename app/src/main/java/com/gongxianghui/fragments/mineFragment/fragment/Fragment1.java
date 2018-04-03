package com.gongxianghui.fragments.mineFragment.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseFragment;
import com.kyleduo.switchbutton.SwitchButton;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class Fragment1 extends BaseFragment {

    private SwitchButton mSwitchButton;


    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_advertise1;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {
        mSwitchButton = getView().findViewById(R.id.switchButton);
        mSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String s;
                if (b) {
                    s = "选中";

                } else {
                    s = "未选中";
                }
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }


}


