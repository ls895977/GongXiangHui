package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.ProtocolActivity;
import com.qunxianghui.gxh.utils.ToMarketUtils;
import com.qunxianghui.gxh.utils.VersonManagerUtils;

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
    @BindView(R.id.iv_aboutus_back)
    ImageView ivAboutusBack;
    @BindView(R.id.tv_appversion)
    TextView tvAppversion;
    @BindView(R.id.iv_aboutus_logo)
    ImageView ivAboutusLogo;
    @BindView(R.id.tv_aboutus_verson_des)
    TextView tvAboutusVersonDes;
    @BindView(R.id.rl_aboutus_quxinghui_business_cloud)
    RelativeLayout rlAboutusQuxinghuiBusinessCloud;
    @BindView(R.id.rl_aboutus_quxinghui_gongxiangshare_cloud)
    RelativeLayout rlAboutusQuxinghuiGongxiangshareCloud;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aboutus;
    }

    @Override
    protected void initViews() {
        searchVersionCode();
    }

    @Override
    protected void initListeners() {
        rlAboutusQuxinghui.setOnClickListener(this);
        rlAboutusQuxinghuiPlatform.setOnClickListener(this);
        rlAboutusQuxinghuiShareCloud.setOnClickListener(this);
        rlAboutusQuxinghuiBusinessCloud.setOnClickListener(this);
        rlAboutusQuxinghuiGongxiangshareCloud.setOnClickListener(this);
        rlAboutusGotoGrade.setOnClickListener(this);
        rlAboutusNewfunIntro.setOnClickListener(this);
        ivAboutusBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_aboutus_quxinghui:
                intent = new Intent(mContext, QunxiangHuiActivity.class);
                intent.putExtra("tag", 1);
                startActivity(intent);
                break;
            case R.id.rl_aboutus_quxinghui_platform:
                intent = new Intent(mContext, QunxiangHuiActivity.class);
                intent.putExtra("tag", 2);
                startActivity(intent);
                break;
            case R.id.rl_aboutus_quxinghui_share_cloud:
                intent = new Intent(mContext, QunxiangHuiActivity.class);
                intent.putExtra("tag", 3);
                startActivity(intent);
                break;
                case R.id.rl_aboutus_quxinghui_business_cloud:
                intent = new Intent(mContext, QunxiangHuiActivity.class);
                intent.putExtra("tag", 4);
                startActivity(intent);
                break;
                case R.id.rl_aboutus_quxinghui_gongxiangshare_cloud:
                intent = new Intent(mContext, QunxiangHuiActivity.class);
                intent.putExtra("tag", 5);
                startActivity(intent);
                break;
            case R.id.rl_aboutus_goto_grade:
                ToMarketUtils.goToMarket(mContext, "com.qunxianghui.gxh");
                break;
            case R.id.rl_aboutus_newfun_intro:
                intent = new Intent(mContext, ProtocolActivity.class);
                intent.putExtra("title", "新功能介绍");
                intent.putExtra("url", Constant.NEW_FUNCTION_URL);
                intent.putExtra("tag", 2);
                startActivity(intent);
                break;
            case R.id.iv_aboutus_back:
                finish();
                break;

        }
    }

    private void searchVersionCode() {
        String versonName = VersonManagerUtils.packageName(mContext);
        int versonCode = VersonManagerUtils.packageCode(mContext);

        if (versonName.equals("4.2.0") && versonCode == 420) {
            tvAppversion.setText("当前已是最新版本");

        } else {
            tvAppversion.setText("去更新");
            tvAppversion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToMarketUtils.goToMarket(mContext, "com.qunxianghui.gxh");
                }
            });

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
