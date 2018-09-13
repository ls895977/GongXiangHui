package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.Activity;
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
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.AddAdvert;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.bean.ShareInfo;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.utils.GlideImageLoader;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.ScreenUtils;
import com.qunxianghui.gxh.widget.Banner;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/2 0002.
 */
public class AddAdvertActivity extends BaseActivity {

    final Activity activity = this;
    @BindView(R.id.webView_mineFragment_Adver)
    WebView webViewMineFragmentAdver;
    @BindView(R.id.banner_top)
    Banner mBannerTop;
    @BindView(R.id.banner_bottom)
    Banner mBannerBottom;
    @BindView(R.id.rl_bottom)
    RelativeLayout mRlBottom;

    private UMShareListener umShareListener;
    private String url;
    //添加位置0底部，1顶部，2贴片
    private int mAddPosition = -1;
    private Dialog dialog;
    private UMWeb web;
    private ArrayList<String> mImages;
    private ShareInfo.ShareInfoBean mData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_advert;
    }

    @Override
    protected void initViews() {
        //微信
        new TitleBuilder(this).setTitleText("编辑广告").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }).setRightIco(R.mipmap.addadver_share).setRightIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getShareInfo();

            }
        });
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        mImages = intent.getStringArrayListExtra("images");
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
                Toast.makeText(AddAdvertActivity.this, " 分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(AddAdvertActivity.this, " 分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(AddAdvertActivity.this, " 分享取消", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void getShareInfo() {
        String[] split = url.split("=");
        String uuid = split[split.length - 1];
        if (getIntent().getBooleanExtra("isPaste", false)) {
            uuid = getIntent().getStringExtra("uuid");
        }
        OkGo.<ShareInfo>post(Constant.GET_SHARE_INFO)
                .params("uuid", uuid)
                .execute(new JsonCallback<ShareInfo>() {
                    @Override
                    public void onSuccess(Response<ShareInfo> response) {
                        if (response.body().code == 200) {
                            mData = response.body().data;

                            if (mData != null) {
                                startThirdShare(mData.imgUrl, mData.title, mData.desc);
                            }
                        } else {
                            asyncShowToast(response.body().message);
                        }
                    }

                    @Override
                    public void onError(Response<ShareInfo> response) {
                        super.onError(response);
                        asyncShowToast(response.message());
                    }
                });
    }

    /**
     * 三方分享
     */
    private void startThirdShare(String url, String title, String descrip) {
        //以下代码是分享示例代码
        UMImage image;
        if (url != null && !url.isEmpty()) {
            image = new UMImage(this, url);//分享图标
        } else {
            image = new UMImage(this, R.mipmap.logo);
        }
        //切记切记 这里分享的链接必须是http开头
        web = new UMWeb( mData.url);
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(descrip);//描述
        View view = LayoutInflater.from(mContext).inflate(R.layout.third_share_self, null);
        RelativeLayout rl_share_wx = view.findViewById(R.id.rl_share_wx);
        RelativeLayout rl_share_wxfriend = view.findViewById(R.id.rl_share_wxfriend);
        RelativeLayout rl_share_qq = view.findViewById(R.id.rl_share_qq);
        RelativeLayout rl_share_qqzone = view.findViewById(R.id.rl_share_qqzone);
        RelativeLayout rl_share_sina = view.findViewById(R.id.rl_share_sina);
        RelativeLayout rl_share_link = view.findViewById(R.id.rl_share_link);
        TextView share_cancel_btn = view.findViewById(R.id.share_cancel_btn);
        // 设置style 控制默认dialog带来的边距问题
        dialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.rl_share_wx:
                        new ShareAction(AddAdvertActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_wxfriend:
                        new ShareAction(AddAdvertActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qq:
                        new ShareAction(AddAdvertActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qqzone:
                        new ShareAction(AddAdvertActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_sina:
                        new ShareAction(AddAdvertActivity.this).setPlatform(SHARE_MEDIA.SINA)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_link:
                        ClipContent();
                        dialog.dismiss();

                        break;
                    case R.id.share_cancel_btn:
                        dialog.dismiss();
                        break;
                }
                dialog.dismiss();
            }
        };
        rl_share_wx.setOnClickListener(listener);
        rl_share_wxfriend.setOnClickListener(listener);
        rl_share_qq.setOnClickListener(listener);
        rl_share_qqzone.setOnClickListener(listener);
        rl_share_sina.setOnClickListener(listener);
        rl_share_link.setOnClickListener(listener);
        share_cancel_btn.setOnClickListener(listener);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        lp.width = (int) display.getWidth();  //设置宽度
        lp.y = 5;  //设置dialog距离底部的距离
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    /*粘贴url*/
    private void ClipContent() {
        ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newRawUri(TAG, Uri.parse(String.valueOf(mData.url)));
        mClipboardManager.setPrimaryClip(clipData);
        asyncShowToast("复制成功");
    }

    @Override
    protected void initData() {
        OkGo.<AddAdvert>get(Constant.GET_ADVERT)
                .params("position", 2)
                .execute(new JsonCallback<AddAdvert>() {
                    @Override
                    public void onSuccess(Response<AddAdvert> response) {
                        AddAdvert body = response.body();
                        if (body != null && body.code == 200) {
                            if (body.data.top != null && !body.data.top.isEmpty()) {
                                mBannerTop.setVisibility(View.VISIBLE);
                                addBanner(mBannerTop, body.data.top);
                            } else {
                                mBannerTop.setVisibility(View.GONE);
                            }
                            if (body.data.bottom != null && !body.data.bottom.isEmpty()) {
                                mBannerBottom.setVisibility(View.VISIBLE);
                                for (EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert : body.data.bottom) {
                                    if (companyAdvert.ad_type == 1 && companyAdvert.is_slide == 1) {
                                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mRlBottom.getLayoutParams();
                                        layoutParams.height = ScreenUtils.dp2px(150);
                                        mRlBottom.setPadding(0, 0, 0, 0);
                                        mRlBottom.setLayoutParams(layoutParams);
                                        List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> list = new ArrayList<>();
                                        list.add(companyAdvert);
                                        addBanner(mBannerBottom, list);
                                        return;
                                    }
                                }
                                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mRlBottom.getLayoutParams();
                                layoutParams.height = ScreenUtils.dp2px(100);
                                int padding = ScreenUtils.dp2px(8);
                                mRlBottom.setPadding(padding, padding, padding, padding);
                                mRlBottom.setLayoutParams(layoutParams);
                                addBanner(mBannerBottom, body.data.bottom);
                            } else {
                                mBannerBottom.setVisibility(View.GONE);
                                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mRlBottom.getLayoutParams();
                                layoutParams.height = ScreenUtils.dp2px(100);
                                mRlBottom.setLayoutParams(layoutParams);
                            }
                        }
                    }
                });
    }

    private void goToAdvertTemplateActivity() {
        Intent intent = new Intent(this, AdvertTemplateActivity.class);
        intent.putExtra("position", mAddPosition);
        intent.putExtra("addtype", mAddPosition);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0x0022) {
            initData();
        }
    }

    @OnClick({R.id.rl_top, R.id.rl_bottom})
    public void onViewClicked(View view) {
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
        switch (view.getId()) {
            case R.id.rl_top:
                if (!SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
                    Toast.makeText(AddAdvertActivity.this, "亲，非企业会员只可添加底部广告哦～～", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAddPosition = 1;
                break;
            case R.id.rl_bottom:
                mAddPosition = 0;
                break;
        }
        goToAdvertTemplateActivity();
    }

    private void addBanner(Banner banner, List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> list) {
        List<String> imags = new ArrayList<>();

        banner.setImages(list)
                .setDelayTime(3000);
        if (banner.getId() == R.id.banner_top) {
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    if (!LoginMsgHelper.isLogin()) {
                        toActivity(LoginActivity.class);
                        return;
                    }
                    if (!SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
                        Toast.makeText(AddAdvertActivity.this, "亲，非企业会员只可添加底部广告哦～～", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mAddPosition = 1;
                    goToAdvertTemplateActivity();
                }
            });
        } else {
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    if (!LoginMsgHelper.isLogin()) {
                        toActivity(LoginActivity.class);
                        return;
                    }
                    mAddPosition = 0;
                    goToAdvertTemplateActivity();
                }
            });
        }
        banner.setImageLoader(new GlideImageLoader())
                .start();
    }
}
