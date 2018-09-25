package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.utils.SavePicByUrlUtils;
import com.qunxianghui.gxh.utils.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;

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
    @BindView(R.id.iv_quxnxianghui_back)
    ImageView ivQuxnxianghuiBack;

    private Bitmap mIcon;
    private Dialog mDialog;
    private UMWeb mWeb;
    private UMShareListener umShareListener;
    private UMImage mImage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qunxianghui;
    }

    @Override
    protected void initListeners() {
        tvQuxianghuiShare.setOnClickListener(this);
        tvQuxianghuiSavetoPhone.setOnClickListener(this);
        ivQuxnxianghuiBack.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        int tag = intent.getIntExtra("tag", 0);

        if (tag == 1) {
            tvQunxianghui.setText("群享汇(订阅号)");
            tvQuxinghuiDes.setText(
                    "区域互联网项目,中小微企业服务平台,让创业更容易!");
            ivQunxianghuiScan.setImageResource(R.mipmap.icon_qunxianghui_dingyue);
            mIcon = BitmapFactory.decodeResource(this.getResources(),
                    R.mipmap.icon_qunxianghui_dingyue);
        } else if (tag == 2) {
            tvQunxianghui.setText("群享汇平台");
            tvQuxinghuiDes.setText(
                    "群享汇平台是包含本地资讯、本地服务、本地精选 \n" +
                            "本地社区的一站式“互联网+本地经济平台”，是为本\n" +
                            "地商户提供互联网广告、互联网营销、社交电商的\n" +
                            "一站式解决方案平台。");
            ivQunxianghuiScan.setImageResource(R.mipmap.icon_qunxianghui_platform);
            mIcon = BitmapFactory.decodeResource(this.getResources(),
                    R.mipmap.icon_qunxianghui_platform);
        } else if (tag == 3) {
            tvQunxianghui.setText("群享汇分享云");
            tvQuxinghuiDes.setText(
                    "群享汇分享云是社交网络拓客工具，为广大商家解决\n" +
                            "了以朋友圈为主的社交圈广告智能投放问题，同时也\n" +
                            "是本地商家异业间的广告互推体系，让推广效果几何\n" +
                            "倍增。");
            ivQunxianghuiScan.setImageResource(R.mipmap.icon_qun_sharecliude);
            mIcon = BitmapFactory.decodeResource(this.getResources(),
                    R.mipmap.icon_qun_sharecliude);
        }
    }

    @Override
    protected void initData() {
        //此回调用于分享
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(QunxiangHuiActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(QunxiangHuiActivity.this, " 分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(QunxiangHuiActivity.this, " 分享取消", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_quxianghui_share:
                startThirdShare();
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
            case R.id.iv_quxnxianghui_back:
                finish();
                break;
        }
    }

    /*三方分享*/
    private void startThirdShare() {
        //以下代码是分享示例代码
        mImage = new UMImage(this, mIcon);
        View view = LayoutInflater.from(mContext).inflate(R.layout.third_share_self2, null);
        RelativeLayout rl_share_wx = view.findViewById(R.id.rl_share_wx);
        RelativeLayout rl_share_wxfriend = view.findViewById(R.id.rl_share_wxfriend);
        RelativeLayout rl_share_qq = view.findViewById(R.id.rl_share_qq);
        RelativeLayout rl_share_qqzone = view.findViewById(R.id.rl_share_qqzone);
        RelativeLayout rl_share_sina = view.findViewById(R.id.rl_share_sina);
        RelativeLayout rl_share_link = view.findViewById(R.id.rl_share_link);
        TextView share_cancel_btn = view.findViewById(R.id.share_cancel_btn);
        // 设置style 控制默认dialog带来的边距问题
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.rl_share_wx:
                        new ShareAction(QunxiangHuiActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                .withMedia(mImage)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_wxfriend:
                        new ShareAction(QunxiangHuiActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                .withMedia(mImage)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qq:
                        new ShareAction(QunxiangHuiActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                .withMedia(mImage)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qqzone:
                        new ShareAction(QunxiangHuiActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                .withMedia(mImage)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_sina:
                        new ShareAction(QunxiangHuiActivity.this).setPlatform(SHARE_MEDIA.SINA)
                                .withMedia(mImage)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_link:
                        mDialog.dismiss();
                        break;
                    case R.id.share_cancel_btn:
                        mDialog.dismiss();
                        break;
                }
                mDialog.dismiss();
            }
        };
        rl_share_wx.setOnClickListener(listener);
        rl_share_wxfriend.setOnClickListener(listener);
        rl_share_qq.setOnClickListener(listener);
        rl_share_qqzone.setOnClickListener(listener);
        rl_share_sina.setOnClickListener(listener);
        rl_share_link.setOnClickListener(listener);
        share_cancel_btn.setOnClickListener(listener);

        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        lp.width = (int) display.getWidth();  //设置宽度
        lp.y = 5;  //设置dialog距离底部的距离
        dialogWindow.setAttributes(lp);
        mDialog.show();
    }
}
