package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.third.sina.Constants;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.OnLongClick;

import static com.qunxianghui.gxh.base.MyApplication.WeiXinAPP_ID;

/**
 * Created by Administrator on 2018/4/2 0002.
 */
public class AddAdverActivity extends BaseActivity implements View.OnClickListener, WbShareCallback {

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
    private WbShareHandler mWbShareHandler;
    private IWXAPI api;
    private String title = "第一条信息分享成功";

    @Override
    protected int getLayoutId() {

        return R.layout.activity_mine_advertise;

    }

    @Override
    protected void initViews() {

        WbSdk.install(this, new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));//创建微博API接口类对象
        mWbShareHandler = new WbShareHandler(this);

        mWbShareHandler.registerApp();
        //微信
        api = WXAPIFactory.createWXAPI(this, WeiXinAPP_ID, true);
        //将应用的appid注册到微信
        api.registerApp(WeiXinAPP_ID);

        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }).setRightIco(R.mipmap.icon_share).setRightIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "跳转到分享页面", Toast.LENGTH_SHORT).show();
                sendMessageToWb(false, true);

                //        //初始化一个WXTextObject
                WXTextObject textObject = new WXTextObject();
                textObject.text = title;
               //用wxTextObjecet对象初始化一个WXMediaMessage对象
                final WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = textObject;
                msg.description = title;
                //构造一个Reg
                final SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());  //transaction字段用于唯一标识一个请求
                req.message = msg;
                //调用api接口发送数据到微信
                api.sendReq(req);


            }
        });


    }

    private void sendMessageToWb(boolean hastText, boolean hasImage) {
        final WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        if (hastText) {
            weiboMultiMessage.textObject = getTextObj();
        }
        if (hasImage) {
            weiboMultiMessage.imageObject = getImageObj(mContext);
        }
        mWbShareHandler.shareMessage(weiboMultiMessage, false);
    }

    @Override
    protected void initDatas() {
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
        webViewMineFragmentAdver.loadUrl("https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_118373008387232010%22%7D&n_type=0&p_from=1");

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
                startActivity(intent);
                break;
            case R.id.rl_mineFragment_addBottomAdver:
                intent = new Intent(this, AdvertisActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onWbShareSuccess() {

        Toast.makeText(activity, "分享成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onWbShareCancel() {
        Toast.makeText(activity, "取消分享", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWbShareFail() {

        Toast.makeText(activity, "分享失败", Toast.LENGTH_SHORT).show();
    }

    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = "我正在使用微博客户端发博器分享文字";
        textObject.title = "1024wawa";
        textObject.actionUrl = "http://www.baidu.com";
        return textObject;
    }

    /**
     * 创建图片消息对象。
     *
     * @return 图片消息对象。
     */
    private ImageObject getImageObj(Context context) {
        ImageObject imageObject = new ImageObject();
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_share);
        imageObject.setImageObject(bitmap);
        return imageObject;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        mWbShareHandler.doResultIntent(intent, this);
    }


}