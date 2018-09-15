package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_aboutus_quxinghui)
    RelativeLayout rlAboutusQuxinghui;
    @BindView(R.id.rl_aboutus_quxinghui_platform)
    RelativeLayout rlAboutusQuxinghuiPlatform;
    @BindView(R.id.rl_aboutus_quxinghui_share_cloud)
    RelativeLayout rlAboutusQuxinghuiShareCloud;
    @BindView(R.id.rl_aboutus_goto_grade)
    RelativeLayout rlAboutusGotoGrade;
    @BindView(R.id.rl_aboutus_newfun_intro)
    RelativeLayout rlAboutusNewfunIntro;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aboutus;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void initListeners() {
        rlAboutusQuxinghui.setOnClickListener(this);
        rlAboutusQuxinghuiPlatform.setOnClickListener(this);
        rlAboutusQuxinghuiShareCloud.setOnClickListener(this);
        rlAboutusGotoGrade.setOnClickListener(this);
        rlAboutusNewfunIntro.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.rl_aboutus_quxinghui:
                intent=new Intent(mContext,QunxiangHuiActivity.class);
                intent.putExtra("tag",1);
                startActivity(intent);

                break;
            case R.id.rl_aboutus_quxinghui_platform:
                intent=new Intent(mContext,QunxiangHuiActivity.class);
                intent.putExtra("tag",2);
                startActivity(intent);

                break;
            case R.id.rl_aboutus_quxinghui_share_cloud:
                intent=new Intent(mContext,QunxiangHuiActivity.class);
                intent.putExtra("tag",3);
                startActivity(intent);

                break;
            case R.id.rl_aboutus_goto_grade:
                asyncShowToast("准备跳到应用商店评分");
                break;
            case R.id.rl_aboutus_newfun_intro:
                asyncShowToast("接入h5 的新功能介绍");
                break;

        }

    }
}
