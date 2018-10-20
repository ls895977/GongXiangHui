package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.view.CoverFlowViewPager;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.StatusBarColorUtil;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 会员升级界面
 * Created by Administrator on 2018/4/16 0016.
 */
public class MemberUpActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_memberup_back)
    ImageView ivMemberupBack;
    @BindView(R.id.tv_memberup_company_one)
    TextView tvMemberupCompanyOne;
    @BindView(R.id.tv_memberup_company_two)
    TextView tvMemberupCompanyTwo;
    @BindView(R.id.tv_memberup_company_three)
    TextView tvMemberupCompanyThree;
    @BindView(R.id.tv_memberup_company_four)
    TextView tvMemberupCompanyFour;
    @BindView(R.id.tv_memberup_company_five)
    TextView tvMemberupCompanyFive;
    @BindView(R.id.viewpager)
    CoverFlowViewPager viewpager;
    private String selfcompayname;
    private String expire_time;
    private String avatar;
    private List<View> viewList = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_up;
    }

    @Override
    protected void setStatusBarTextColor() {
        StatusBarColorUtil.setStatusTextColor(false, this);
    }
    @Override
    protected void initViews() {
        SharedPreferences companyData = getSharedPreferences("companymessage", 0);
        selfcompayname = companyData.getString("selfcompayname", "");
        expire_time = companyData.getString("member_expire_time", "");
        avatar = companyData.getString("avatar", "");
        for (int i = 0; i < 3; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_card_upgrade_layout, null, false);
            //todo 1 设置数据，两个数据时添加第二个数据循环三次
            RoundImageView roundImageHead = view.findViewById(R.id.iv_company_head);
            TextView mTvMemberupStep = view.findViewById(R.id.tv_memberup_company_state);
            TextView mTvMemberUpActiviteTime = view.findViewById(R.id.tv_memberup_activite_time);
            TextView mTvMemberUpActiviteActive = view.findViewById(R.id.tv_memberup_quickly_active);
            if (SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
                if (i==1){
                    mTvMemberUpActiviteActive.setVisibility(View.GONE);
                    mTvMemberUpActiviteTime.setVisibility(View.VISIBLE);
                    mTvMemberupStep.setText("会员状态: 已激活");
                    mTvMemberUpActiviteTime.setText(expire_time + " 到期");
                    ((TextView) view.findViewById(R.id.tv_memberup_company_step)).setText("企业会员");
                }else {
                    mTvMemberUpActiviteActive.setVisibility(View.GONE);
                    mTvMemberUpActiviteTime.setVisibility(View.GONE);
                    mTvMemberupStep.setText("会员状态:正常");
                    ((TextView) view.findViewById(R.id.tv_memberup_company_step)).setText("注册会员");
                }

            } else {
                if (i==1){
                    ((TextView) view.findViewById(R.id.tv_memberup_company_step)).setText("企业会员");
                    mTvMemberUpActiviteActive.setVisibility(View.VISIBLE);
                    mTvMemberupStep.setText("会员状态: 未激活");
                    view.findViewById(R.id.tv_memberup_quickly_active).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            toActivityWithResult(MemberUpActiveActivity.class, 0x0011);
                        }
                    });
                }else {
                    mTvMemberUpActiviteActive.setVisibility(View.GONE);
                    mTvMemberupStep.setText("会员状态: 正常");
                    ((TextView) view.findViewById(R.id.tv_memberup_company_step)).setText("注册会员");
                }
            }
            RequestOptions options = new RequestOptions();
            options.circleCrop();
            options.centerCrop();
            options.placeholder(R.mipmap.user_moren);
            options.error(R.mipmap.default_img);
            Glide.with(mContext).load(avatar).apply(options).into(roundImageHead);
            viewList.add(view);
        }
        viewpager.setViewList(viewList);
        viewpager.setCurrentItem(1);
        viewpager.setOnPageSelectListener(new CoverFlowViewPager.OnCoverPageSelectListener() {
            @Override
            public void select(int pos) {
                //todo 1 切换升级的身份后重新设置下面的数据
                if (pos == 1) {
                    tvMemberupCompanyFive.setVisibility(View.VISIBLE);
                    tvMemberupCompanyOne.setText("支持微信/网易等外部文章以及" + "\n" + "app内部文章植入广告后分享");
                    tvMemberupCompanyTwo.setText("顶部广告、底部广告都可编辑");
                    tvMemberupCompanyThree.setText("广告储存模板21个（顶部、底部和贴片）");
                    tvMemberupCompanyFour.setText("顶部通栏广告限10个底部所有广告" + "\n" + "限10个，贴片广告限1个");
                    tvMemberupCompanyFive.setText("企业员工排行榜");

                } else {
                    tvMemberupCompanyOne.setText("限app内部文章植入广告后分享");
                    tvMemberupCompanyTwo.setText("仅限底部广告编辑");
                    tvMemberupCompanyThree.setText("编辑底部所有广告限2个");
                    tvMemberupCompanyFour.setText("广告储存模板限2个（限底部）");
                    tvMemberupCompanyFive.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_memberup_back:
                finish();
                break;
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ivMemberupBack.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0x0022) {
            resetCardShow(viewpager.getCurrentItemIndex());
        }else  if (resultCode==0x0011){
            resetCardShow(viewpager.getCurrentItemIndex());
        }
    }

    private void resetCardShow(int currentItemIndex) {

        SharedPreferences companyData = getSharedPreferences("companymessage", 0);
        selfcompayname = companyData.getString("selfcompayname", "");
        expire_time = companyData.getString("member_expire_time", "");
        avatar = companyData.getString("avatar", "");

        View view = viewList.get(currentItemIndex);
        //todo 1
            TextView mTvMemberupStep = view.findViewById(R.id.tv_memberup_company_state);
            TextView mTvMemberUpActiviteTime = view.findViewById(R.id.tv_memberup_activite_time);
            TextView mTvMemberUpActiviteActive = view.findViewById(R.id.tv_memberup_quickly_active);
            if (SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
                    mTvMemberUpActiviteActive.setVisibility(View.GONE);
                    mTvMemberUpActiviteTime.setVisibility(View.VISIBLE);
                    mTvMemberupStep.setText("会员状态: 已激活");
                    mTvMemberUpActiviteTime.setText(expire_time + " 到期");
                    ((TextView) view.findViewById(R.id.tv_memberup_company_step)).setText("企业会员");
                } else {
                    mTvMemberUpActiviteActive.setVisibility(View.GONE);
                    mTvMemberUpActiviteTime.setVisibility(View.GONE);
                    mTvMemberupStep.setText("会员状态:正常");
                    ((TextView) view.findViewById(R.id.tv_memberup_company_step)).setText("注册会员");
                }
            }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
