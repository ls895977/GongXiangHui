package com.qunxianghui.gxh.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.location.CommentBean;
import com.qunxianghui.gxh.bean.location.MyCollectBean;
import com.qunxianghui.gxh.bean.mine.MyColleNewsDetailBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.mineFragment.activity.AddAdverActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;


/**
 * 新闻详情界面
 * Created by Administrator on 2018/4/9 0009.
 */

public class NewsDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_input_discuss)
    EditText etInputDiscuss;
    @BindView(R.id.ll_input_discuss)
    LinearLayout llInputDiscuss;
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
    private Dialog dialog;
    private View alertView;
    private TextView tv_addAdver_share;
    private TextView tv_article_share;
    private TextView tv_bottom_alertdialog_cancle;
    private LinearLayout ll_share_list;
    private String url;
    private int uuid;
    private int id;
    private UMShareListener umShareListener;
    private android.os.Handler handler = new android.os.Handler();
    private String title;
    private TextView btn_submit;
    private EditText inputComment;
    private PopupWindow popupWindow;
    private boolean has_collect = false;
    private ClipboardManager mClipboardManager;
    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_news_detail;
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initViews() {
        //这句是调取粘贴的系统服务
        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        mWebView = (WebView) findViewById(R.id.wed_news_detail);
        //微信朋友圈
        mProgressBar = (ProgressBar) findViewById(R.id.progress_newsdetail);
        mWebView.loadUrl(this.url);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        uuid = intent.getIntExtra("uuid", 0);
        id = intent.getIntExtra("id", 0);
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
        //此回调用于分享
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(NewsDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(NewsDetailActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(NewsDetailActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };
    }
    @Override
    protected void initDatas() {
        HoldeNewsDetail();
    }

    private void HoldeNewsDetail() {
        OkGo.<String>post(Constant.GET_NEWS_CONTENT_DETAIL_URL)
                .params("id", uuid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ParseNewsDetailData(response.body());
                    }
                });
    }

    private void ParseNewsDetailData(String body) {
        MyColleNewsDetailBean newsDetailBean = GsonUtils.jsonFromJson(body, MyColleNewsDetailBean.class);
        if (newsDetailBean.getCode() == 0) {
            MyColleNewsDetailBean.DataBean dataList = newsDetailBean.getData();

            has_collect = dataList.isHas_collect();
            if (has_collect) {
                ivNewsDetailCollect.setBackgroundResource(R.drawable.collect);
            } else {
                ivNewsDetailCollect.setBackgroundResource(R.drawable.collect_normal);
            }
        }
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
        ll_share_list = alertView.findViewById(R.id.ll_share_list);
        tv_addAdver_share.setOnClickListener(this);
        tv_article_share.setOnClickListener(this);
        tv_bottom_alertdialog_cancle.setOnClickListener(this);
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
        lp.y = 5;  //设置dialog距离底部的距离
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();
    }
    @Override
    protected void initListeners() {
        etInputDiscuss.setOnClickListener(this);
        ivNewsDetailAddAdver.setOnClickListener(this);
        ivNewsDetailCollect.setOnClickListener(this);
        ivNewsDetailShare.setOnClickListener(this);
        ivNewsDetailMessage.setOnClickListener(this);
    }

    private void SettingsP() {
        WebSettings settings = mWebView.getSettings();
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
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("用户单机超链接", url);
                //判断用户单击的是那个超链接
                String tag = "tel";
                if (url.contains(tag)) {
                    final String mobile = url.substring(url.lastIndexOf("/") + 1);
                    Log.e("mobile----------->", mobile);
                    final Intent mIntent = new Intent(Intent.ACTION_CALL);
                    final Uri data = Uri.parse(mobile);
                    mIntent.setData(data);
                    if (ActivityCompat.checkSelfPermission(NewsDetailActivity.this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(mIntent);
                        //这个超连接,java已经处理了，webview不要处理
                        return true;
                    } else {
                        //申请权限
                        ActivityCompat.requestPermissions(NewsDetailActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                        return true;
                    }
                }
                return false;
            }
        });
        mWebView.loadUrl(url);
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
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.et_input_discuss:
                showPopupCommnet();
                break;
            case R.id.tv_addAdver_share:
                Toast.makeText(mContext, "点击添加广告分享", Toast.LENGTH_SHORT).show();
                intent = new Intent(mContext, AddAdverActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
                dialog.dismiss();
                break;
            case R.id.tv_article_share:
                ll_share_list.setVisibility(View.GONE);
//                StartThirdShare();
                showShareDialog();
                break;
            case R.id.tv_bottom_alertdialog_cancle:
                dialog.dismiss();
                break;
            case R.id.iv_news_detail_addAdver:
                intent = new Intent(mContext, AddAdverActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
                break;
            case R.id.iv_news_detail_collect:
                CollectDataList(uuid);
                break;
            case R.id.iv_news_detail_share:
                showBottomAliert();
                break;
            case R.id.iv_news_detail_message:
                showPopupCommnet();
                break;
        }
    }
    /*三方分享唤起*/
    private void showShareDialog() {
        //以下代码是分享示例代码
        UMImage image = new UMImage(this, R.mipmap.logo);//分享图标
        final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头
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
                        new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_wxfriend:
                        new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qq:
                        new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_qqzone:
                        new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                .withMedia(web)
                                .setCallback(umShareListener)
                                .share();
                        break;
                    case R.id.rl_share_sina:
                        new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.SINA)
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
        ClipData clipData = ClipData.newRawUri(TAG, Uri.parse(url));
        mClipboardManager.setPrimaryClip(clipData);
        asyncShowToast("复制成功");
        dialog.dismiss();

    }

    /*弹出评论框*/
    @SuppressLint("WrongConstant")
    private void showPopupCommnet() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_popupwindow, null);
        inputComment = view.findViewById(R.id.et_discuss);
        btn_submit = (TextView) view.findViewById(R.id.tv_confirm);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                }
                return false;
            }
        });
        popupWindow.setFocusable(true);
        //设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(true);
        //设置弹出窗体需要软键盘
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        // 再设置模式，和Activity的一样，覆盖，调整大小。
        popupWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ColorDrawable cd = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(cd);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        //    WindowManager.LayoutParams params = getWindow().getAttributes();
//    params.alpha = 0.4f;
//    getWindow().setAttributes(params);
        // 设置popWindow的显示和消失动画
//    popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.update();
//        popupInputMethodWindow();

        /**
         * 提交评论
         */
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CommonText = inputComment.getText().toString().trim();
                if (TextUtils.isEmpty(CommonText)) {

                    asyncShowToast("请输入评论内容");
                } else {
                    RequestNewsCommon(CommonText);

                }
            }
        });
    }

    /**
     * 请求评论
     *
     * @param commonText
     */
    private void RequestNewsCommon(String commonText) {
        OkGo.<LzyResponse<CommentBean>>post(Constant.ISSURE_DISUSS_URL)
                .params("uuid", uuid)
                .params("content", commonText)
                .execute(new DialogCallback<LzyResponse<CommentBean>>(NewsDetailActivity.this) {
                    @Override
                    public void onSuccess(Response<LzyResponse<CommentBean>> response) {
                        if (response.body().code == 0) {
                            asyncShowToast("评论成功");
                            llInputDiscuss.setVisibility(View.VISIBLE);
                            mWebView.reload();
                            popupWindow.dismiss();

                        }
                    }
                });
    }

//    /**
//     * 三方分享
//     */
//    private void StartThirdShare() {
//        //以下代码是分享示例代码
//        UMImage image = new UMImage(this, R.mipmap.logo);//分享图标
//        final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头
//        web.setTitle(title);//标题
//        web.setThumb(image);  //缩略图
////        web.setDescription("你要分享内容的描述");//描述
//        new ShareAction(this)
//                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA)
//                .setShareboardclickCallback(new ShareBoardlistener() {
//                    @Override
//                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
//                        if (share_media == SHARE_MEDIA.QQ) {
//                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.QQ)
//                                    .withMedia(web)
//                                    .setCallback(umShareListener)
//                                    .share();
//                        } else if (share_media == SHARE_MEDIA.WEIXIN) {
//                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
//                                    .withMedia(web)
//                                    .setCallback(umShareListener)
//                                    .share();
//                        } else if (share_media == SHARE_MEDIA.QZONE) {
//                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.QZONE)
//                                    .withMedia(web)
//                                    .setCallback(umShareListener)
//                                    .share();
//                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
//                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
//                                    .withMedia(web)
//                                    .setCallback(umShareListener)
//                                    .share();
//                        } else if (share_media == SHARE_MEDIA.SINA) {
//                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.SINA)
//                                    .withMedia(web)
//                                    .setCallback(umShareListener)
//                                    .share();
//                        }
//                    }
//                }).open();
//    }

    private void CollectDataList(int uuid) {
        OkGo.<String>post(Constant.ADD_COLLECT_URL)
                .params("data_uuid", uuid).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseCollectData(response.body());
            }
        });
    }

    private void parseCollectData(String body) {
        final MyCollectBean myCollectBean = GsonUtil.parseJsonWithGson(body, MyCollectBean.class);
        if (myCollectBean.getCode() == 0) {
            asyncShowToast("收藏成功");
            ivNewsDetailCollect.setBackgroundResource(R.drawable.collect);

        } else if (myCollectBean.getCode() == 202) {
            asyncShowToast("取消收藏成功");
            ivNewsDetailCollect.setBackgroundResource(R.drawable.collect_normal);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
