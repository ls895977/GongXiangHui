package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.utils.SavePicByUrlUtils;
import com.qunxianghui.gxh.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QunxiangHuiActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_qunxianghui)
    TextView tvQunxianghui;
    @BindView(R.id.iv_qunxianghui_scan)
    ImageView ivQunxianghuiScan;
    @BindView(R.id.tv_quxinghui_des)
    TextView tvQuxinghuiDes;
    @BindView(R.id.tv_quxianghui_saveto_phone)
    TextView tvQuxianghuiSavetoPhone;
    @BindView(R.id.tv_quxianghui_share)
    TextView tvQuxianghuiShare;
    private Bitmap mIcon;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qunxianghui;
    }

    @Override
    protected void initListeners() {
        tvQuxianghuiShare.setOnClickListener(this);
        tvQuxianghuiSavetoPhone.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        int tag = intent.getIntExtra("tag", 0);
        mIcon = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.icon_aboutus_scan);


        if (tag == 1) {
            tvQunxianghui.setText("群享汇");
            tvQuxinghuiDes.setText("群享汇(订阅号)\n" +
                    "区域互联网项目,中小微企业服务平台,让创业更容易");
            ivQunxianghuiScan.setImageResource(R.mipmap.icon_aboutus_scan);

        } else if (tag == 2) {
            tvQunxianghui.setText("群享汇平台");
            tvQuxinghuiDes.setText("群享汇平台（服务号）)\n" +
                    "群享汇平台是包含本地资讯、本地服务、本地精选 \n" +
                    "本地社区的一站式“互联网+本地经济平台”，是为本\n" +
                    "地商户提供互联网广告、互联网营销、社交电商的\n" +
                    "一站式解决方案平台。");
        } else if (tag == 3) {
            tvQunxianghui.setText("群享汇分享云");

            tvQuxinghuiDes.setText("群享汇分享云\n" +
                    "群享汇分享云是社交网络拓客工具，为广大商家解决\n" +
                    "了以朋友圈为主的社交圈广告智能投放问题，同时也\n" +
                    "是本地商家异业间的广告互推体系，让推广效果几何\n" +
                    "倍增。");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_quxianghui_share:
                asyncShowToast("分享的操作");

                break;
            case R.id.tv_quxianghui_saveto_phone:

                if (SavePicByUrlUtils.sIsSaving) {
                    ToastUtils.showShort("图片正在保存中，请稍后...");
                    return;
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SavePicByUrlUtils.savePic2Phone(QunxiangHuiActivity.this, mIcon);
                        }
                    }).start();
                }
                break;
        }
    }
}
