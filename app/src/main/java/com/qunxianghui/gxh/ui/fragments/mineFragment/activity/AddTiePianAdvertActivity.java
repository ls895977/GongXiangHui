package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.bean.PersonalAds;
import com.qunxianghui.gxh.bean.ShareInfo;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/2 0002.
 */
public class AddTiePianAdvertActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv_top)
    TextView mTvTop;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.tv_seconds)
    TextView mTvSeconds;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;

    private UMShareListener umShareListener;
    private String mUrl;
    private Dialog mDialog;
    private UMWeb mWeb;
    private String mTitle;
    private String mDescrip;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tiepian_advert;
    }

    @Override
    protected void initViews() {
        //微信
        new TitleBuilder(this).setTitleText("植入视频广告").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }).setRightIco(R.mipmap.addadver_share).setRightIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
                    getShareInfo();
                } else {
                    asyncShowToast("请升级到企业会员再试!");
                }

            }
        });
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mTitle = intent.getStringExtra("title");
        mDescrip = intent.getStringExtra("descrip");
        //此回调用于分享
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(AddTiePianAdvertActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(AddTiePianAdvertActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(AddTiePianAdvertActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void initData() {
        OkGo.<PersonalAds>get(Constant.GET_AD_LIST)
                .params("position", 4)
                .execute(new JsonCallback<PersonalAds>() {
                    @Override
                    public void onSuccess(Response<PersonalAds> response) {
                        PersonalAds body = response.body();
                        if (body != null && body.code == 200 && !body.data.isEmpty()) {
                            EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = body.data.get(0);
                            Glide.with(AddTiePianAdvertActivity.this).load(companyAdvert.images)
                                    .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(mIv);
                            mTvType.setVisibility(View.VISIBLE);
                            mTvSeconds.setVisibility(View.VISIBLE);
                            mTvTop.setVisibility(View.VISIBLE);
                            switch (companyAdvert.settings.operate) {
                                case 1:
                                    mTvType.setText("图片点击效果：跳转链接");
                                    break;
                                case 2:
                                    mTvType.setText("图片点击效果：拨打电话");
                                    break;
                                case 3:
                                    mTvType.setText("图片点击效果：联系QQ");
                                    break;
                            }
                            mTvTop.setText(String.format("%s广告", companyAdvert.settings.time));
                            mTvSeconds.setText(String.format("广告显示时间：%s", companyAdvert.settings.time));
                        } else {
                            resetView();
                        }
                    }

                    @Override
                    public void onError(Response<PersonalAds> response) {
                        super.onError(response);
                        resetView();
                    }
                });
    }

    private void resetView() {
        mIv.setImageResource(R.mipmap.icon_add_tiepian);
        mTvType.setVisibility(View.GONE);
        mTvSeconds.setVisibility(View.GONE);
        mTvTop.setVisibility(View.GONE);
    }

    private void getShareInfo() {
        String[] split = mUrl.split("=");
        String uuid = split[split.length - 1];
        OkGo.<ShareInfo>post(Constant.GET_SHARE_INFO)
                .params("uuid", uuid)
                .execute(new JsonCallback<ShareInfo>() {
                    @Override
                    public void onSuccess(Response<ShareInfo> response) {
                        if (response.body().code == 200) {
                            ShareInfo.ShareInfoBean data = response.body().data;
                            if (data != null) {
                                startThirdShare(data.url, data.title, data.desc);
                            }
//                            else if (r == 105) {
//                                Toast.makeText(activity, "请在首次会员激活的设备上进行分享", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    }
                });
    }

    /**
     * 三方分享
     */
    private void startThirdShare(String url, String title, String desc) {
        //以下代码是分享示例代码
        UMImage image = new UMImage(this, R.mipmap.logo);//分享图标
        //切记切记 这里分享的链接必须是http开头

        mWeb = new UMWeb(url);
        mWeb.setTitle(title);//标题
        mWeb.setThumb(image);  //缩略图
        if (mDescrip!=null){
           mWeb.setDescription(mDescrip);
        }else {
            mWeb.setDescription("群享汇-中小微企业成长平台，让创业更容易！");//描述
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.third_share_self, null);
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
                        new ShareAction(AddTiePianAdvertActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                .withMedia(mWeb)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_wxfriend:
                        new ShareAction(AddTiePianAdvertActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                .withMedia(mWeb)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qq:
                        new ShareAction(AddTiePianAdvertActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                .withMedia(mWeb)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qqzone:
                        new ShareAction(AddTiePianAdvertActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                .withMedia(mWeb)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_sina:
                        new ShareAction(AddTiePianAdvertActivity.this).setPlatform(SHARE_MEDIA.SINA)
                                .withMedia(mWeb)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_link:
                        ClipContent();
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

        //获取当前activity所在的窗体
        final Window dialogWindow = mDialog.getWindow();
        //设置dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        final WindowManager windowManager = getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        lp.width = (int) display.getWidth();  //设置宽度
        lp.y = 5;  //设置dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        mDialog.show();

    }

    /*粘贴url*/
    private void ClipContent() {
        ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newRawUri(TAG, Uri.parse(String.valueOf(mWeb)));
        mClipboardManager.setPrimaryClip(clipData);
        asyncShowToast("复制成功");
        mDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0x0022) {
            initData();
        }
    }

    @OnClick(R.id.rl_add)
    public void onViewClicked() {
        if (!SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
            asyncShowToast("亲，非企业会员只可添加底部广告哦！");
            return;
        }else {
            Intent intent = new Intent(this, AdvertTemplateActivity.class);
            intent.putExtra("position", 2);
            startActivityForResult(intent, 100);
        }

    }
}
