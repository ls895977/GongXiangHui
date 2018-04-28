package com.qunxianghui.gxh.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.AddAdverActivity;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qunxianghui.gxh.base.MyApplication.WeiXinAPP_ID;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class NewsDetailActivity extends BaseActivity implements View.OnClickListener, WbShareCallback {

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
    @BindView(R.id.iv_news_detail_addAdver)
    ImageView ivNewsDetailAddAdver;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    public static final String url = "http://new.qq.com/omn/20180409/20180409C0446D.html";
    private Dialog dialog;
    private View alertView;
    private TextView tv_addAdver_share;
    private TextView tv_article_share;
    private TextView tv_bottom_alertdialog_cancle;
    private Tencent mTencent;
    private MyIUiListener mIUiListener;
    private LinearLayout ll_shared_third_list;
    private LinearLayout ll_share_list;
    private ImageView iv_newsdetail_shared_weichat;
    private ImageView iv_newsdetail_wxshared_friendcircle;
    private ImageView iv_newsdetail_shared_sina;
    private ImageView iv_newsdetail_shared_qq;
    private Button bt_newsdetail_cancle_share;
    private IWXAPI api;
    private String userText = "微信分享的第一条内容：奋战20天夺取开门红";
    private WbShareHandler mWbShareHandler;
    private Bundle params;
    private MyIUiListener qqShareListener;

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return R.layout.activity_news_detail;

    }

    @Override
    protected void initViews() {
        //微博
        WbSdk.install(this, new AuthInfo(this, com.qunxianghui.gxh.third.sina.Constants.APP_KEY, com.qunxianghui.gxh.third.sina.Constants.REDIRECT_URL, com.qunxianghui.gxh.third.sina.Constants.SCOPE));//创建微博API接口类对象
        mWbShareHandler = new WbShareHandler(this);
        mWbShareHandler.registerApp();
        mWebView = (WebView) findViewById(R.id.wed_news_detail);

        //微信
        api = WXAPIFactory.createWXAPI(this, WeiXinAPP_ID, true);
        //微信朋友圈

        //QQ
        if (mTencent == null) {
            mTencent = Tencent.createInstance("1106763297", getApplicationContext());
            qqShareListener = new MyIUiListener();
        }
        //QQ空间


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
            private View inflate;

            @Override
            public void onClick(View v) {

                showBottomAliert();

            }
        });
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


    //底部弹出对话框
    private void showBottomAliert() {
        dialog = new Dialog(NewsDetailActivity.this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        alertView = LayoutInflater.from(mContext).inflate(R.layout.bottom_alertdialog, null);
        //初始化控件
        tv_addAdver_share = alertView.findViewById(R.id.tv_addAdver_share);
        tv_article_share = alertView.findViewById(R.id.tv_article_share);
        tv_bottom_alertdialog_cancle = alertView.findViewById(R.id.tv_bottom_alertdialog_cancle);
        ll_shared_third_list = alertView.findViewById(R.id.ll_shared_third_list);
        ll_share_list = alertView.findViewById(R.id.ll_share_list);
        iv_newsdetail_shared_qq = alertView.findViewById(R.id.iv_newsdetail_shared_qq);
        iv_newsdetail_shared_weichat = alertView.findViewById(R.id.iv_newsdetail_shared_weichat);
        iv_newsdetail_shared_sina = alertView.findViewById(R.id.iv_newsdetail_shared_sina);
        iv_newsdetail_wxshared_friendcircle = alertView.findViewById(R.id.iv_newsdetail_wxshared_friendcircle);
        bt_newsdetail_cancle_share = alertView.findViewById(R.id.bt_newsdetail_cancle_share);
        tv_addAdver_share.setOnClickListener(this);
        tv_article_share.setOnClickListener(this);
        tv_bottom_alertdialog_cancle.setOnClickListener(this);
        bt_newsdetail_cancle_share.setOnClickListener(this);
        //点击了三方的分享
        iv_newsdetail_shared_sina.setOnClickListener(this);
        iv_newsdetail_shared_weichat.setOnClickListener(this);
        iv_newsdetail_wxshared_friendcircle.setOnClickListener(this);
        iv_newsdetail_shared_qq.setOnClickListener(this);
        //将布局设置给dialog
        dialog.setContentView(alertView);
        //获取当前activity所在的窗体
        final Window dialogWindow = dialog.getWindow();
        //设置dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        final WindowManager windowManager = getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        lp.width = (int) display.getWidth();  //设置宽度
        lp.y = 20;  //设置dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    @Override
    protected void initListeners() {

        etInputDiscuss.setOnClickListener(this);
        llNewsDetailBigDiss.setOnClickListener(this);
        ivNewsDetailAddAdver.setOnClickListener(this);

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

            case R.id.tv_addAdver_share:
                Toast.makeText(mContext, "点击添加广告分享", Toast.LENGTH_SHORT).show();
                toActivity(AddAdverActivity.class);


                break;
            case R.id.tv_article_share:
                Toast.makeText(mContext, "文章直接分享", Toast.LENGTH_SHORT).show();
                ll_share_list.setVisibility(View.GONE);
                ll_shared_third_list.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_bottom_alertdialog_cancle:
                dialog.dismiss();
                break;
            case R.id.iv_news_detail_addAdver:

                toActivity(AddAdverActivity.class);
                break;

            case R.id.iv_newsdetail_shared_qq:
                asyncShowToast("点击了分享QQ");
                shareQQ();

                dialog.dismiss();
                break;
            case R.id.iv_newsdetail_shared_weichat:

                asyncShowToast("点击了分享微信");
                shareWeiChat(true);

                dialog.dismiss();
                break;

            case R.id.iv_newsdetail_wxshared_friendcircle:
                //分享到朋友圈
                shareWeiChat(false);
                break;
            case R.id.iv_newsdetail_shared_sina:
                asyncShowToast("点击分享了新浪微博");
                shareSina();
                dialog.dismiss();
                break;
            case R.id.bt_newsdetail_cancle_share:
                dialog.dismiss();
                break;
        }

    }

    //分享QQ
    private void shareQQ() {
        params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "标题");// 标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");// 摘要
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "应用名称");// 应用名称
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com/news/1.html");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
        // 分享操作要在主线程中完成
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mTencent.shareToQQ(NewsDetailActivity.this, params, qqShareListener);
            }
        });

    }

    //暂时还没加QQ空间分享 先写上
    private void shareToQQzone() {
        params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "标题");// 标题
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");// 摘要
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com/news/1.html");// 内容地址
        ArrayList<String> imgUrlList = new ArrayList<>();
        imgUrlList.add("http://f.hiphotos.baidu.com/image/h%3D200/sign=6f05c5f929738bd4db21b531918a876c/6a600c338744ebf8affdde1bdef9d72a6059a702.jpg");
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imgUrlList);// 图片地址
        // 分享操作要在主线程中完成
        ThreadManager.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                mTencent.shareToQzone(NewsDetailActivity.this, params, qqShareListener);
            }
        });
    }


    //分享微博
    private void shareSina() {
        sendMessageToWb(false, true);
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

    //分享微信
    private void shareWeiChat(boolean isShareFriend) {

        //        //初始化一个WXTextObject
        WXTextObject textObject = new WXTextObject();
        textObject.text = userText;
//用wxTextObjecet对象初始化一个WXMediaMessage对象
        final WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObject;
        msg.description = userText;
        //构造一个Reg
        final SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");  //transaction字段用于唯一标识一个请求
        req.message = msg;
        req.scene = isShareFriend ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        //调用api接口发送数据到微信
        api.sendReq(req);

    }





    @Override
    public void onWbShareSuccess() {
        Toast.makeText(mContext, "分享成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWbShareCancel() {
        Toast.makeText(mContext, "取消分享", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWbShareFail() {
        Toast.makeText(mContext, "分享失败", Toast.LENGTH_SHORT).show();
    }

    //QQ分享
    class MyIUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            asyncShowToast("分享成功");
        }

        @Override
        public void onError(UiError uiError) {
            asyncShowToast("分享失败");
        }

        @Override
        public void onCancel() {
            asyncShowToast("分享取消");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, mIUiListener);
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

}