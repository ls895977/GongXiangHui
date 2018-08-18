package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.CompanySetActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class ProtocolActivity extends BaseActivity implements View.OnClickListener {

    final Activity activity = this;
    @BindView(R.id.ll_protocol_main)
    LinearLayout llProtocolMain;
    @BindView(R.id.iv_webback)
    ImageView ivWebback;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_newsdetail_issue)
    TextView tvNewsdetailIssue;
    @BindView(R.id.rl_protocol_title)
    RelativeLayout mRlProtocolTitle;
    private WebView webView;
    private Dialog loadingDialog;
    private String mToken;
    private StringBuffer mBuffer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void initViews() {
        loadingDialog = createLoadingDialog(ProtocolActivity.this, "加载中...");
        loadingDialog.show();
    }

    private Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);//得到加载view
        LinearLayout layout = v.findViewById(R.id.dialog_view);//加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = v.findViewById(R.id.dialog_img);
        TextView tipTextView = v.findViewById(R.id.tipTextView);// 提示文字
        //加载动画
        final Animation animation = AnimationUtils.loadAnimation(context, R.anim.load_animation);
        //使用imageView显示动画
        spaceshipImage.startAnimation(animation);
        tipTextView.setText(msg);  //设置加载信息
        final Dialog loadingDialog = new Dialog(context);
        loadingDialog.setCancelable(true);  //不可以用返回键 取消
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)); //设置布局
        return loadingDialog;

    }

    @SuppressLint("NewApi")
    @Override
    protected void initData() {
        Intent intent = getIntent();
        final String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        mToken = intent.getStringExtra("token");
        int tag = intent.getIntExtra("tag", 0);
        if (tag == 1) {
            tvNewsdetailIssue.setVisibility(View.GONE);
            tvTitle.setVisibility(View.GONE);
            mBuffer = new StringBuffer(url);
            mBuffer.append("?token=" + mToken);
            mRlProtocolTitle.setVisibility(View.GONE);
        }
        tvTitle.setText(title);
        webView = new WebView(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, 25, 0, 0);
        webView.setLayoutParams(params);
        llProtocolMain.addView(webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(false);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDisplayZoomControls(false);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAppCacheEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                activity.setTitle("Loading...");
                activity.setProgress(progress * 100);
                if (progress == 100) {
                    activity.setTitle(title);
                    loadingDialog.dismiss();
                }
            }
        });


        webView.setWebViewClient(new WebViewClient() {

            private Intent mMIntent;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Logger.d("shouldOverrideUrlLoading--->:" + url);
                Log.e("用户单机超链接", url);
                //判断用户单机的是那个超链接
                String tag = "tel";
                if (url.contains(tag)) {
                    final String mobile = url.substring(url.lastIndexOf("/") + 1);
                    Log.e("mobile----------->", mobile);
                    if (mobile.equals("tel:1516715042")) {
                        ProtocolActivity.this.finish();
                    } else {
                        mMIntent = new Intent(Intent.ACTION_CALL);
                        final Uri data = Uri.parse(mobile);
                        mMIntent.setData(data);
                        if (ActivityCompat.checkSelfPermission(ProtocolActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                            startActivity(mMIntent);
                            //这个超连接,java已经处理了，webview不要处理
                            return true;
                        } else {
                            //申请权限
                            ActivityCompat.requestPermissions(ProtocolActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                            return true;
                        }
                    }


                }
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                Log.d("onReceivedSslError: ", "" + error.getPrimaryError());
                if (error.getPrimaryError() == SslError.SSL_DATE_INVALID
                        || error.getPrimaryError() == SslError.SSL_EXPIRED
                        || error.getPrimaryError() == SslError.SSL_INVALID
                        || error.getPrimaryError() == SslError.SSL_UNTRUSTED) {

                    handler.proceed();

                } else {
                    handler.cancel();
                }

            }
        });
        webView.loadUrl(String.valueOf(mBuffer));

        Logger.d("initDatas--->:");
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.resumeTimers();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
        webView.pauseTimers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView.clearHistory();
        webView.destroy();
        webView = null;
        llProtocolMain.removeAllViews();
        llProtocolMain = null;
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ivWebback.setOnClickListener(this);
        tvNewsdetailIssue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_webback:
                finish();
                break;
            case R.id.tv_newsdetail_issue:
                toActivity(CompanySetActivity.class);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webView.canGoBack()) {
            webView.goBack();

        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
