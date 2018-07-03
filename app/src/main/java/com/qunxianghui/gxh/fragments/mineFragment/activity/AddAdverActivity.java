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
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/2 0002.
 */
public class AddAdverActivity extends BaseActivity implements View.OnClickListener {
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

    private UMShareListener umShareListener;
    private String url;
    private String title;
    private int mTopId;
    private int mMiddleId;
    private int mVideoId;
    private int mBottomId;
    private int mIsFloat;
    //添加位置0顶部，1中间，2底部
    private int mAddPosition = -1;

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
                getShareInfo();
            }
        });
    }

    private void getShareInfo() {
        String[] split = url.split("/");
        String uuid = split[split.length - 1].split("\\.")[0];
        OkGo.<String>post(Constant.GET_SHARE_INFO)
                .params("uuid", uuid)
                .params("topAd", mTopId)
                .params("middleAd", mMiddleId)
                .params("videoAd", mVideoId)
                .params("bottomAd", mBottomId)
                .params("isFloat", mIsFloat)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            JSONObject data = jsonObject.getJSONObject("data");
                            int code = jsonObject.getInt("code");
                            if (code == 0 && data != null) {
                                startThirdShare(data.getString("url"), data.getString("title"), data.getString("imgUrl"));
                            }else if (code==105){
                                asyncShowToast("请在首次会员激活的设备上进行分享");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    /**
     * 三方分享
     */
    private void startThirdShare(String url, String title, String imgUrl) {
        //以下代码是分享示例代码
        UMImage image = new UMImage(this, R.mipmap.logo);//分享图标
        final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
//        web.setDescription("你要分享内容的描述");//描述
        new ShareAction(this)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.QQ) {
                            new ShareAction(AddAdverActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN) {
                            new ShareAction(AddAdverActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.QZONE) {
                            new ShareAction(AddAdverActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
//                                    new ShareAction(LoginActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
//                                            .withMedia(web)
//                                            .setCallback(umShareListener)
//                                            .share();
                        }
                    }
                }).open();

    }

    @Override
    protected void initDatas() {
        final Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
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


        //此回调用于分享
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(AddAdverActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(AddAdverActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(AddAdverActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };
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
                if (!SPUtils.getBoolean(AddAdverActivity.this, SpConstant.IS_COMPANY, false)) {
                    Toast.makeText(AddAdverActivity.this, "亲，非企业会员只可添加底部广告哦～～", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAddPosition = 0;
                intent = new Intent(this, AdvertisConmmengtActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.rl_mineFragment_addBottomAdver:
                mAddPosition = 2;
                intent = new Intent(this, AdvertisConmmengtActivity.class);
                startActivityForResult(intent, 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0x0022) {
            if (mAddPosition == 0) {
                mTopId = data.getIntExtra("ad_id", 0);
                String url = data.getStringExtra("url");
                GlideApp.with(mContext).load(url)
                        .centerCrop()
                        .placeholder(R.mipmap.icon_headimage)
                        .error(R.mipmap.icon_headimage)
                        .into(ivMineFragmentAddTopAdver);
            } else if (mAddPosition == 2) {
                mBottomId = data.getIntExtra("ad_id", 0);
                String url = data.getStringExtra("url");
                GlideApp.with(mContext).load(url)
                        .centerCrop()
                        .placeholder(R.mipmap.icon_headimage)
                        .error(R.mipmap.icon_headimage)
                        .into(ivMineFragmentAddBottomAdver);
            }
        }
    }
}
