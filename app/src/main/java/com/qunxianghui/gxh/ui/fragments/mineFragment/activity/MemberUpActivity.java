package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 会员升级界面
 * Created by Administrator on 2018/4/16 0016.
 */

public class MemberUpActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ll_memberup_company_state)
    LinearLayout mLlMemberupCompanyState;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_up;
    }

    @Override
    protected void initViews() {
        super.initViews();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLlMemberupCompanyState.getLayoutParams();
        layoutParams.width = width * 5/ 6;
        mLlMemberupCompanyState.setLayoutParams(layoutParams);

    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void initListeners() {
        super.initListeners();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
