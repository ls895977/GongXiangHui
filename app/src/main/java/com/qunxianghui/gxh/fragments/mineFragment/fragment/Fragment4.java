package com.qunxianghui.gxh.fragments.mineFragment.fragment;


import android.view.View;
import android.view.WindowManager;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class Fragment4 extends BaseFragment {

    @Override
    protected void onLoadData() {

    }

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_advertise4;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {

    }
}
