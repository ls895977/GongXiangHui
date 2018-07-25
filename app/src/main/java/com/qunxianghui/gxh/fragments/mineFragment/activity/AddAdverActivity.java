package com.qunxianghui.gxh.fragments.mineFragment.activity;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

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
    private Dialog dialog;
    private UMWeb web;
    private RequestOptions options;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_advertise;
    }

    @Override
    protected void initViews() {
        //微信
        new TitleBuilder(this).setTitleText("编辑广告").setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
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
                            } else if (code == 105) {
                                Toast.makeText(activity, "请在首次会员激活的设备上进行分享", Toast.LENGTH_SHORT).show();
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
        //切记切记 这里分享的链接必须是http开头
        web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
//        web.setDescription("你要分享内容的描述");//描述
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
                        new ShareAction(AddAdverActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_wxfriend:
                        new ShareAction(AddAdverActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qq:
                        new ShareAction(AddAdverActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qqzone:
                        new ShareAction(AddAdverActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_sina:
                        new ShareAction(AddAdverActivity.this).setPlatform(SHARE_MEDIA.SINA)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_link:
                        ClipContent();
                        break;
                    case R.id.share_cancel_btn:
                        dialog.dismiss();
                        break;
                }
            }
        };
        rl_share_wx.setOnClickListener(listener);
        rl_share_wxfriend.setOnClickListener(listener);
        rl_share_qq.setOnClickListener(listener);
        rl_share_qqzone.setOnClickListener(listener);
        rl_share_sina.setOnClickListener(listener);
        rl_share_link.setOnClickListener(listener);
        share_cancel_btn.setOnClickListener(listener);

        //获取当前activity所在的窗体
        final Window dialogWindow = dialog.getWindow();
        //设置dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        final WindowManager windowManager = getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        lp.width = (int) display.getWidth();  //设置宽度
        lp.y = 5;  //设置dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();

    }
    /*粘贴url*/
    private void ClipContent() {
        ClipboardManager mClipboardManager =(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newRawUri(TAG, Uri.parse(String.valueOf(web)));
        mClipboardManager.setPrimaryClip(clipData);
        asyncShowToast("复制成功");
        dialog.dismiss();
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

                options = new RequestOptions();
                options.placeholder(R.mipmap.icon_headimage);
                options.error(R.mipmap.icon_headimage);
                options.centerCrop();
                Glide.with(mContext).load(url).apply(options).into(ivMineFragmentAddTopAdver);

            } else if (mAddPosition == 2) {
                mBottomId = data.getIntExtra("ad_id", 0);
                String url = data.getStringExtra("url");

                options.placeholder(R.mipmap.icon_headimage);
                options.error(R.mipmap.icon_headimage);
                options.centerCrop();
                Glide.with(mContext).load(url).apply(options).into(ivMineFragmentAddBottomAdver);
            }
        }
    }
}
