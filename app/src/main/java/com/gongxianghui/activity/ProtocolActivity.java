package com.gongxianghui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.utils.StatusBarUtil;
import com.gongxianghui.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by hegeyang on 2017/11/17.
 */

public class ProtocolActivity extends BaseActivity {
    @BindView(R.id.ll_protocol_main)
    LinearLayout llProtocolMain;
    private WebView webview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        webview = new WebView(this);
        WebSettings settings = webview.getSettings();
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDisplayZoomControls(false);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAppCacheEnabled(true);
        webview.loadUrl(url);
        llProtocolMain.addView(webview);
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
        webview.removeAllViews();
        webview.clearHistory();
        webview.destroy();
        webview=null;
        llProtocolMain.removeAllViews();
        llProtocolMain = null;
    }
}
