package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class ProtocolActivity extends BaseActivity {
    final Activity activity = this;
    @BindView(R.id.ll_protocol_main)
    LinearLayout llProtocolMain;
    private WebView webView;
    private Dialog loadingDialog;


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
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);//加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.dialog_img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
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

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        final String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");

        new TitleBuilder(this).setTitleText(title).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
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
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("用户单机超链接",url);
                //判断用户单机的是那个超链接
                String tag ="tel";
                if (url.contains(tag)) {
                    final String mobile = url.substring(url.lastIndexOf("/") + 1);
                    Log.e("mobile----------->", mobile);
                    final Intent mIntent = new Intent(Intent.ACTION_CALL);
                    final Uri data = Uri.parse(mobile);
                    mIntent.setData(data);
                    if (ActivityCompat.checkSelfPermission(ProtocolActivity.this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(mIntent);
                        //这个超连接,java已经处理了，webview不要处理
                        return true;
                    } else {
                        //申请权限
                        ActivityCompat.requestPermissions(ProtocolActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        return true;
                    }
                }
                return false;
            }
        });
        webView.loadUrl(url);

        llProtocolMain.addView(webView);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
}
