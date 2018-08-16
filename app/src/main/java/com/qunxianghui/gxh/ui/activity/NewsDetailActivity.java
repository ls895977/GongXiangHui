package com.qunxianghui.gxh.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.MyCollectNewsDetailBean;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AddAdverActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.CompanySetActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 新闻详情界面
 * Created by Administrator on 2018/4/9 0009.
 */

public class NewsDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.progress_newsdetail)
    ProgressBar mProgressNewsdetail;
    @BindView(R.id.tv_newsdetail_issue)
    TextView mTvNewsdetailIssue;
    @BindView(R.id.wed_news_detail)
    WebView mWedNewsDetail;
    private Dialog mShareDialog;
    private Dialog mUmShareDialog;
    private String url;
    private int uuid;
    private int id;
    private UMShareListener umShareListener;
    private Handler handler = new Handler();
    private String title;
    private EditText inputComment;
    private PopupWindow popupWindow;
    private boolean has_collect = false;
    private ClipboardManager mClipboardManager;
    private MyCollectNewsDetailBean.DataBean mDataList;
    private String mToken;
    private StringBuffer mBuffer;

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initViews() {
        //这句是调取粘贴的系统服务
        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        uuid = intent.getIntExtra("uuid", 0);
        id = intent.getIntExtra("id", 0);
        mToken = intent.getStringExtra("token");
            mBuffer = new StringBuffer(url);
            mBuffer.append("?token=" + mToken);
            mBuffer.append("&uuid=" + uuid);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {
        //此回调用于分享
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(NewsDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(NewsDetailActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(NewsDetailActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };

        WebSettings settings = mWedNewsDetail.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDisplayZoomControls(false);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAppCacheEnabled(true);
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
                mProgressNewsdetail.setVisibility(View.VISIBLE);
                return false;
            }
        });
        mWedNewsDetail.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    // 网页加载完成
                    mProgressNewsdetail.setVisibility(View.GONE);
                } else {
                    //加载中
                    mProgressNewsdetail.setProgress(newProgress);
                }
            }
        });
        mWedNewsDetail.loadUrl(String.valueOf(mBuffer));
    }

    @OnClick({R.id.iv_newsdetail_back, R.id.iv_news_detail_topshare, R.id.iv_news_detail_addAdver})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_newsdetail_back:
                finish();
                break;
            case R.id.iv_news_detail_topshare:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                } else {
                    showBottomDialog();
                }

                break;
            case R.id.iv_news_detail_addAdver:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                } else {
                    Intent intent = new Intent(mContext, AddAdverActivity.class);
                    intent.putExtra("url", mBuffer.toString());
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        mShareDialog.dismiss();
        switch (v.getId()) {
            case R.id.tv_addAdver_share:
                Toast.makeText(mContext, "点击添加广告分享", Toast.LENGTH_SHORT).show();
                intent = new Intent(mContext, AddAdverActivity.class);
                intent.putExtra("url", mBuffer.toString());
                startActivity(intent);
                break;
            case R.id.tv_article_share:
                showShareDialog();
                break;
        }
    }

    //底部弹出对话框
    private void showBottomDialog() {
        if (mShareDialog == null) {
            mShareDialog = new Dialog(NewsDetailActivity.this, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            View alertView = LayoutInflater.from(mContext).inflate(R.layout.bottom_alertdialog, null);
            //初始化控件
            alertView.findViewById(R.id.tv_addAdver_share).setOnClickListener(this);
            alertView.findViewById(R.id.tv_article_share).setOnClickListener(this);
            alertView.findViewById(R.id.tv_bottom_alertdialog_cancle).setOnClickListener(this);
            //将布局设置给dialog
            mShareDialog.setContentView(alertView);
            //获取当前activity所在的窗体
            Window dialogWindow = mShareDialog.getWindow();
            //设置dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            lp.width = (int) display.getWidth();  //设置宽度
            lp.y = 5;  //设置dialog距离底部的距离
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
        }
        mShareDialog.show();
    }

    /*三方分享唤起*/
    private void showShareDialog() {
        //以下代码是分享示例代码
        if (mUmShareDialog == null) {
            mUmShareDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
            UMImage image = new UMImage(this, R.mipmap.logo);//分享图标
            final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头
            web.setTitle(title);//标题
            web.setThumb(image);  //缩略图
//        web.setDescription("你要分享内容的描述");//描述
            View view = LayoutInflater.from(mContext).inflate(R.layout.third_share_self, null);
            mUmShareDialog.setContentView(view);
            View.OnClickListener listener = new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.rl_share_wx:
                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                            break;
                        case R.id.rl_share_wxfriend:
                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                            break;
                        case R.id.rl_share_qq:
                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                            break;
                        case R.id.rl_share_qqzone:
                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                            break;
                        case R.id.rl_share_sina:
                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.SINA)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                            break;
                        case R.id.rl_share_link:
                            copyContent();
                            mUmShareDialog.dismiss();
                            break;
                        case R.id.share_cancel_btn:
                            mUmShareDialog.dismiss();
                            break;
                        case R.id.tv_newsdetail_issue:
                            toActivity(CompanySetActivity.class);
                            break;
                    }
                }
            };
            view.findViewById(R.id.rl_share_wx).setOnClickListener(listener);
            view.findViewById(R.id.rl_share_wxfriend).setOnClickListener(listener);
            view.findViewById(R.id.rl_share_qq).setOnClickListener(listener);
            view.findViewById(R.id.rl_share_qqzone).setOnClickListener(listener);
            view.findViewById(R.id.rl_share_sina).setOnClickListener(listener);
            view.findViewById(R.id.rl_share_link).setOnClickListener(listener);
            view.findViewById(R.id.share_cancel_btn).setOnClickListener(listener);
            //获取当前activity所在的窗体
            Window dialogWindow = mUmShareDialog.getWindow();
            //设置dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();

            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            lp.width = (int) display.getWidth();  //设置宽度
            lp.y = 5;  //设置dialog距离底部的距离
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
        }
        mUmShareDialog.show();
    }

    /*粘贴url*/
    private void copyContent() {
        ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newRawUri(TAG, Uri.parse(url));
        mClipboardManager.setPrimaryClip(clipData);
        asyncShowToast("复制成功");
    }



}
