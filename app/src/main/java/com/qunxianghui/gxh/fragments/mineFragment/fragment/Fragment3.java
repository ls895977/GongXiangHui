package com.qunxianghui.gxh.fragments.mineFragment.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class Fragment3 extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.iv_mine_addBigAdver_three)
    ImageView ivMineAddBigAdverThree;
    @BindView(R.id.rl_mine_BigAdver_three)
    LinearLayout rlMineBigAdverThree;
    @BindView(R.id.iv_mine_addFragment1BigAdver)
    ImageView ivMineAddFragment1BigAdver;
    Unbinder unbinder;


    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_advertise3;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    protected void initListeners() {


    }


    @Override
    protected void onLoadData() {

    }


    @Override
    public void onClick(View v) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
