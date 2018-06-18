package com.qunxianghui.gxh.fragments.issureFragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.fragments.locationFragment.LocationFragment;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class IssureFragment extends BaseFragment {
    private static IssureFragment issureFragment;

    @Override
    protected void onLoadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_issure;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {

    }

    public static IssureFragment getInstance() {
        if(issureFragment == null){
            issureFragment = new IssureFragment();
        }
        return issureFragment;
    }
}
