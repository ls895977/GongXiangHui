package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.widget.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 会员升级界面
 * Created by Administrator on 2018/4/16 0016.
 */

public class MemberUpActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ll_memberup_company_state)
    LinearLayout mLlMemberupCompanyState;
    @BindView(R.id.iv_memberup_back)
    ImageView mIvMemberupBack;
    @BindView(R.id.tv_memberup_quickly_active)
    TextView mTvMemberupQuicklyActive;
    @BindView(R.id.iv_company_head)
    RoundImageView ivCompanyHead;
    @BindView(R.id.tv_memberup_company_step)
    TextView tvMemberupCompanyStep;
    @BindView(R.id.tv_memberup_company_state)
    TextView tvMemberupCompanyState;
    @BindView(R.id.iv_regist_head)
    RoundImageView ivRegistHead;
    @BindView(R.id.tv_memberup_person_step)
    TextView tvMemberupPersonStep;
    @BindView(R.id.tv_memberup_person_state)
    TextView tvMemberupPersonState;
    @BindView(R.id.tv_memberup_activite_time)
    TextView tvMemberupActiviteTime;
    @BindView(R.id.ll_memberup_regist_state)
    LinearLayout llMemberupRegistState;
    @BindView(R.id.tv_memberup_person_active)
    TextView tvMemberupPersonActive;
    @BindView(R.id.cv_company)
    CardView cvCompany;
    @BindView(R.id.cv_register)
    CardView cvRegister;

    private String selfcompayname;
    private String expire_time;
    private String avatar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_up;
    }

    @Override
    protected void initViews() {

        SharedPreferences companyData = getSharedPreferences("companymessage", 0);
        selfcompayname = companyData.getString("selfcompayname", "");
        expire_time = companyData.getString("expire_time", "");
        avatar = companyData.getString("avatar", "");

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cvCompany.getLayoutParams();
        layoutParams.width = width * 4 / 5;
        cvCompany.setLayoutParams(layoutParams);
        cvRegister.setLayoutParams(layoutParams);

    }

    @Override
    protected void initData() {

        RequestOptions options = new RequestOptions();
        options.circleCrop();
        options.centerCrop();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        Glide.with(mContext).load(avatar).apply(options).into(ivCompanyHead);
        Glide.with(mContext).load(avatar).apply(options).into(ivRegistHead);
        if (selfcompayname != null) {
            mTvMemberupQuicklyActive.setVisibility(View.GONE);
            tvMemberupPersonActive.setVisibility(View.GONE);
            tvMemberupActiviteTime.setVisibility(View.VISIBLE);
            tvMemberupCompanyState.setText("会员状态: 已激活");
            tvMemberupActiviteTime.setText("激活日期" + expire_time);
            tvMemberupPersonState.setText("会员状态:正常");

        } else {
            mTvMemberupQuicklyActive.setVisibility(View.VISIBLE);
            tvMemberupPersonActive.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_memberup_back:
                finish();
                break;
            case R.id.tv_memberup_quickly_active:
                toActivity(MemberUpActiveActivity.class);
                break;
            case R.id.tv_memberup_person_active:
                toActivity(MemberUpActiveActivity.class);
                break;
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mIvMemberupBack.setOnClickListener(this);
        mTvMemberupQuicklyActive.setOnClickListener(this);
        tvMemberupPersonActive.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
