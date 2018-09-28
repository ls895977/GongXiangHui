package com.qunxianghui.gxh.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AddAdvertActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AddTiePianAdvertActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.NetWorkUtil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新闻详情界面
 * Created by Administrator on 2018/4/9 0009.
 */

public class NewsDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.wed_news_detail)
    WebView mWedNewsDetail;
    @BindView(R.id.iv_no_network)
    View mIvNoNetwork;
    @BindView(R.id.tv_news_detail_notnet)
    TextView tvNewsDetailNotnet;
    @BindView(R.id.ll_news_detail_notnet)
    LinearLayout llNewsDetailNotnet;
    @BindView(R.id.rl_newsdetail_title)
    RelativeLayout rlNewsdetailTitle;
    @BindView(R.id.iv_news_detail_addAdver)
    ImageView ivNewsDetailAddAdver;
    @BindView(R.id.ll_protocol_main)
    RelativeLayout llProtocolMain;
    private Dialog mLoadingDialog;
    private Dialog mShareDialog;
    private Dialog mUmShareDialog;
    private UMShareListener umShareListener;
    private String url;
    private String title;
    private String mDescrip;
    private List<String> mImages;
    private StringBuffer mBuffer;
    private int mPosition;
    private String mVideoimage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initViews() {
        mLoadingDialog = createLoadingDialog(NewsDetailActivity.this, "加载中...");
        mLoadingDialog.show();
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        mDescrip = intent.getStringExtra("descrip");
        int uuid = intent.getIntExtra("uuid", 0);
        mPosition = intent.getIntExtra("position", 0);
        mImages = intent.getStringArrayListExtra("images");
        String mToken = intent.getStringExtra("token");
        mVideoimage = intent.getStringExtra("videoimage");
        mBuffer = new StringBuffer(url);
        mBuffer.append("?token=").append(mToken).append("&uuid=").append(uuid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWedNewsDetail.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWedNewsDetail.onPause();
    }

    private Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);//得到加载view
        ImageView spaceshipImage = v.findViewById(R.id.dialog_img);
        TextView tipTextView = v.findViewById(R.id.tipTextView);// 提示文字
        final Animation animation = AnimationUtils.loadAnimation(context, R.anim.load_animation);
        spaceshipImage.startAnimation(animation);
        tipTextView.setText(msg);  //设置加载信息
        final Dialog loadingDialog = new Dialog(context);
        loadingDialog.setCancelable(true);  //不可以用返回键 取消
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)); //设置布局
        return loadingDialog;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initListeners() {
        WebSettings settings = mWedNewsDetail.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setBlockNetworkImage(false); // 解决图片不显示
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDisplayZoomControls(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAppCacheEnabled(true);
        settings.setUseWideViewPort(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }



        mWedNewsDetail.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress >= 80) {
                    mLoadingDialog.dismiss();
                }
            }

        });
        mWedNewsDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //判断用户单击的是那个超链接
                String tag = "tel";
                if (url.contains(tag)) {
                    String mobile = url.substring(url.lastIndexOf("/") + 1);
                    Intent mIntent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse(mobile);
                    mIntent.setData(data);
                    if (ActivityCompat.checkSelfPermission(NewsDetailActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(mIntent);
                        //这个超连接,java已经处理了，webview不要处理
                        return true;
                    } else {
                        //申请权限
                        ActivityCompat.requestPermissions(NewsDetailActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                com.orhanobut.logger.Logger.e("网页加载失败"+error);
            }
        });
        mWedNewsDetail.loadUrl(String.valueOf(mBuffer));
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                asyncShowToast(platform + " 分享成功");
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                asyncShowToast(platform + " 分享失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                asyncShowToast(platform + " 分享取消");
            }
        };
        if (!NetWorkUtil.isNetWorkAvailable(NewsDetailActivity.this)) {
            mWedNewsDetail.setVisibility(View.GONE);
            llNewsDetailNotnet.setVisibility(View.VISIBLE);
            tvNewsDetailNotnet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }else {
            tvNewsDetailNotnet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mWedNewsDetail.reload();
                    llNewsDetailNotnet.setVisibility(View.GONE);
                }
            });
        }

    }

    @OnClick({R.id.iv_newsdetail_back, R.id.iv_news_detail_topshare, R.id.iv_news_detail_addAdver})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_newsdetail_back:
                setResult(0x0077);
                finish();
                break;
            case R.id.iv_news_detail_topshare:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                } else {
                    showBottomDialog();
                }
                break;
            case R.id.iv_news_detail_addAdver:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                } else {
                    Intent intent;
                    if (mPosition == 4) {
                        intent = new Intent(mContext, AddTiePianAdvertActivity.class);
                    } else {
                        intent = new Intent(mContext, AddAdvertActivity.class);
                    }
                    intent.putExtra("url", mBuffer.toString());
                    intent.putExtra("title", title);
                    intent.putExtra("descrip", mDescrip);
                    intent.putStringArrayListExtra("images", (ArrayList<String>) mImages);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        mShareDialog.dismiss();
        switch (v.getId()) {
            case R.id.tv_addAdver_share:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                } else {
                    Intent intent;
                    if (mPosition == 4) {
                        if (!SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
                            asyncShowToast("亲，非企业会员只可添加底部广告哦～～");
                            return;
                        }
                        intent = new Intent(mContext, AddTiePianAdvertActivity.class);
                    } else {
                        intent = new Intent(mContext, AddAdvertActivity.class);
                    }
                    intent.putExtra("url", mBuffer.toString());
                    intent.putExtra("title", title);
                    intent.putExtra("descrip", mDescrip);
                    startActivity(intent);
                }
                break;
            case R.id.tv_article_share:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                } else {
                    showShareDialog();
                }
                break;
        }
    }

    //底部弹出对话框
    private void showBottomDialog() {
        if (mShareDialog == null) {
            mShareDialog = new Dialog(NewsDetailActivity.this, R.style.ActionSheetDialogStyle);
            View alertView = LayoutInflater.from(mContext).inflate(R.layout.bottom_alertdialog, null);
            alertView.findViewById(R.id.tv_addAdver_share).setOnClickListener(this);
            alertView.findViewById(R.id.tv_article_share).setOnClickListener(this);
            alertView.findViewById(R.id.tv_bottom_alertdialog_cancle).setOnClickListener(this);
            mShareDialog.setContentView(alertView);
            Window dialogWindow = mShareDialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            lp.width = (int) display.getWidth();  //设置宽度
            lp.y = 5;  //设置dialog距离底部的距离
            dialogWindow.setAttributes(lp);
        }
        mShareDialog.show();
    }

    private void showShareDialog() {
        if (mUmShareDialog == null) {
            mUmShareDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
            UMImage image;
            final UMWeb web = new UMWeb(mBuffer.toString()); //切记切记 这里分享的链接必须是http开头
            if (mPosition == 4) {
                if (mVideoimage == null) {
                    image = new UMImage(this, R.mipmap.icon_video_share);
                } else {
                    image = new UMImage(this, mVideoimage);
                }

                web.setDescription(!TextUtils.isEmpty(mDescrip) ? mDescrip : "群享汇-中小微企业成长平台，让创业更容易！");//描述
            } else {
                if (mImages != null && !mImages.isEmpty()) {
                    image = new UMImage(this, mImages.get(0));
                } else {
                    image = new UMImage(this, R.mipmap.logo);
                }
                web.setDescription(mDescrip.substring(0, 70));//描述
            }
            web.setTitle(title);//标题
            web.setThumb(image);  //缩略图
            View view = LayoutInflater.from(mContext).inflate(R.layout.third_share_self, null);
            mUmShareDialog.setContentView(view);
            View.OnClickListener listener = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.rl_share_wx:
                            share(SHARE_MEDIA.WEIXIN);
                            break;
                        case R.id.rl_share_wxfriend:
                            share(SHARE_MEDIA.WEIXIN_CIRCLE);
                            break;
                        case R.id.rl_share_qq:
                            share(SHARE_MEDIA.QQ);
                            break;
                        case R.id.rl_share_qqzone:
                            share(SHARE_MEDIA.QZONE);
                            break;
                        case R.id.rl_share_sina:
                            share(SHARE_MEDIA.SINA);
                            break;
                        case R.id.rl_share_link:
                            copyContent();
                            mUmShareDialog.dismiss();
                            break;
                        case R.id.share_cancel_btn:
                            mUmShareDialog.dismiss();
                            break;

                    }
                    mUmShareDialog.dismiss();
                }

                private void share(SHARE_MEDIA var1) {
                    new ShareAction(NewsDetailActivity.this).setPlatform(var1)
                            .withMedia(web)
                            .setCallback(umShareListener)
                            .share();
                }
            };
            view.findViewById(R.id.rl_share_wx).setOnClickListener(listener);
            view.findViewById(R.id.rl_share_wxfriend).setOnClickListener(listener);
            view.findViewById(R.id.rl_share_qq).setOnClickListener(listener);
            view.findViewById(R.id.rl_share_qqzone).setOnClickListener(listener);
            view.findViewById(R.id.rl_share_sina).setOnClickListener(listener);
            view.findViewById(R.id.rl_share_link).setOnClickListener(listener);
            view.findViewById(R.id.share_cancel_btn).setOnClickListener(listener);
            Window dialogWindow = mUmShareDialog.getWindow();
            dialogWindow.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            lp.width = display.getWidth();
            lp.y = 5;
            dialogWindow.setAttributes(lp);
        }
        mUmShareDialog.show();
    }

    /*粘贴url*/
    private void copyContent() {
        ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newRawUri(TAG, Uri.parse(mBuffer.toString()));
        mClipboardManager.setPrimaryClip(clipData);
        asyncShowToast("复制成功");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
