package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 会员升级界面
 * Created by Administrator on 2018/4/16 0016.
 */

public class MemberUpActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.tv_member_up_quickly_activate)
    TextView tvMemberUpQuicklyActivate;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_up;
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initDatas() {
        new TitleBuilder(MemberUpActivity.this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("产品介绍");

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        tvMemberUpQuicklyActivate.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_member_up_quickly_activate:
                Toast.makeText(mContext, "模拟激活成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
