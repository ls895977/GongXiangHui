package com.qunxianghui.gxh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class NewsDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_input_discuss)
    EditText etInputDiscuss;
    @BindView(R.id.ll_news_detail_bigDiss)
    LinearLayout llNewsDetailBigDiss;
    @BindView(R.id.iv_news_detail_collect)
    ImageView ivNewsDetailCollect;
    @BindView(R.id.iv_news_detail_message)
    ImageView ivNewsDetailMessage;
    @BindView(R.id.iv_news_detail_share)
    ImageView ivNewsDetailShare;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    public static final String url = "http://new.qq.com/omn/20180409/20180409C0446D.html";

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setRightIco(R.mipmap.icon_share).setRightIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "这里是分享界面", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initListeners() {

        etInputDiscuss.setOnClickListener(this);
        llNewsDetailBigDiss.setOnClickListener(this);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_input_discuss:
                etInputDiscuss.setVisibility(View.GONE);
                ivNewsDetailCollect.setVisibility(View.GONE);
                ivNewsDetailMessage.setVisibility(View.GONE);
                ivNewsDetailShare.setVisibility(View.GONE);
                llNewsDetailBigDiss.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_news_detail_bigDiss:
                break;
        }

    }
}
