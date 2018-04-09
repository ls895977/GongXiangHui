package com.gongxianghui.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.lzy.okgo.model.Progress;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class NewsDetailActivity extends BaseActivity {

        private WebView mWebView;
    private ProgressBar mProgressBar;
public static final String url="http://new.qq.com/omn/20180409/20180409C0446D.html";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;

    }

    @Override
    protected void initViews() {
        mWebView = (WebView) findViewById(R.id.wed_news_detail);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_newsdetail);
        Intent intent = getIntent();

        intent.getStringExtra("url");
        mWebView.loadUrl(url);
    }

    @Override
    protected void initDatas() {
       SettingsP();
    }

    private void SettingsP() {
        WebSettings seting = mWebView.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {
                    // 网页加载完成
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    //加载中
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }
    }
