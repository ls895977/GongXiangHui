package com.gongxianghui.fragments.mineFragment.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseFragment;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class Fragment5 extends BaseFragment {

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_advertise5;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {

    }
}
