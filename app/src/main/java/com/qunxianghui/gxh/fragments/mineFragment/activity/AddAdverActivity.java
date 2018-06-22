package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.broadcast.MainBroadCast;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/2 0002.
 */
public class AddAdverActivity extends BaseActivity implements View.OnClickListener{
    final Activity activity = this;
    @BindView(R.id.iv_mineFragment_addTopAdver)
    ImageView ivMineFragmentAddTopAdver;
    @BindView(R.id.tv_mingFragment_addTopAdver)
    TextView tvMingFragmentAddTopAdver;
    @BindView(R.id.webView_mineFragment_Adver)
    WebView webViewMineFragmentAdver;
    @BindView(R.id.iv_mineFragment_addBottomAdver)
    ImageView ivMineFragmentAddBottomAdver;
    @BindView(R.id.tv_mingFragment_addBottomAdver)
    TextView tvMingFragmentAddBottomAdver;
    @BindView(R.id.rl_mineFragment_addTopAdver)
    RelativeLayout rlMineFragmentAddTopAdver;
    @BindView(R.id.rl_mineFragment_addBottomAdver)
    RelativeLayout rlMineFragmentAddBottomAdver;

    private String url;
    private MainBroadCast receiver;
    static final String INTENT_BROADCAST_ADVERTISE = "android.intent.action.advertise";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_advertise;
    }

    @Override
    protected void initViews() {
        //微信
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }).setRightIco(R.mipmap.icon_share).setRightIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void initDatas() {
        final Intent intent = getIntent();
        url = intent.getStringExtra("url");
        WebSettings settings = webViewMineFragmentAdver.getSettings();

        /* 设置支持Js,必须设置的,不然网页基本上不能看 */
        settings.setJavaScriptEnabled(true);
        /* 设置缓存模式,我这里使用的默认,不做多讲解 */
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        /* 设置为使用webview推荐的窗口 */
        settings.setUseWideViewPort(true);
        /* 设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用 */
        settings.setLoadWithOverviewMode(true);
        /* HTML5的地理位置服务,设置为true,启用地理定位 */
        settings.setGeolocationEnabled(true);
        /* 设置是否允许webview使用缩放的功能,我这里设为false,不允许 */
        settings.setBuiltInZoomControls(false);
        /* 设置显示水平滚动条,就是网页右边的滚动条.我这里设置的不显示 */
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(false);
        /* 设置显示水平滚动条,就是网页右边的滚动条.我这里设置的显示 */
        webViewMineFragmentAdver.setHorizontalScrollBarEnabled(true);
        /* 指定垂直滚动条是否有叠加样式 */
        webViewMineFragmentAdver.setVerticalScrollBarEnabled(true);
        /* 设置滚动条的样式 */
        webViewMineFragmentAdver.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        /* 这个不用说了,重写WebChromeClient监听网页加载的进度,从而实现进度条 */
        webViewMineFragmentAdver.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                activity.setProgress(newProgress * 100);
                if (newProgress == 100) {
//                    activity.setTitle();
                    //加载成功后显示的内容
                }
                super.onProgressChanged(view, newProgress);

            }
        });

        webViewMineFragmentAdver.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webViewMineFragmentAdver.loadUrl(url);

        /* 同上,重写WebViewClient可以监听网页的跳转和资源加载等等... */
        webViewMineFragmentAdver.setWebViewClient(new WebViewClient());


    }

    @Override
    protected void initListeners() {
        rlMineFragmentAddTopAdver.setOnClickListener(AddAdverActivity.this);
        rlMineFragmentAddBottomAdver.setOnClickListener(AddAdverActivity.this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_mineFragment_addTopAdver:
                intent = new Intent(this, AdvertisActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, 100);
                break;
            case R.id.rl_mineFragment_addBottomAdver:
                intent = new Intent(this, AdvertisConmmengtActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100){
            String url = data.getStringExtra("url");
            String title = data.getStringExtra("title");
            GlideApp.with(mContext).load(url)
                    .centerCrop()
                    .placeholder(R.mipmap.icon_headimage)
                    .error(R.mipmap.icon_headimage)
                    .into(ivMineFragmentAddTopAdver);
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }
}
