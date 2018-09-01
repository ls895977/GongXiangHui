package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.StatusBarColorUtil;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

/**
 * 会员升级界面
 * Created by Administrator on 2018/4/16 0016.
 */

public class MemberUpActivity extends BaseActivity implements View.OnClickListener {

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
    protected void setStatusBarTextColor(){
        StatusBarColorUtil.setStatusTextColor(false,this);
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
        if (SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
            mTvMemberupQuicklyActive.setVisibility(View.GONE);
            tvMemberupPersonActive.setVisibility(View.GONE);
            tvMemberupActiviteTime.setVisibility(View.VISIBLE);
            tvMemberupCompanyState.setText("会员状态: 已激活");
            Date date = new Date(Long.parseLong(expire_time) * 1000);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            tvMemberupActiviteTime.setText("到期日期 : " + simpleDateFormat.format(date));
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
            case R.id.tv_memberup_person_active:
                toActivityWithResult(MemberUpActiveActivity.class, 0x0011);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0x0022) {
            initViews();
            initData();
        }
    }
}
