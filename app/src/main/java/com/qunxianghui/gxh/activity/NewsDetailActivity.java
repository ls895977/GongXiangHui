package com.qunxianghui.gxh.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
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
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.mineFragment.activity.AddAdverActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.HttpStatusUtil;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.json.JSONObject;

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
    private ImageView iv_newsdetail_wxshared_friendcircle;

    private Bundle params;
    private String url;
    private int uuid;
    private int id;

    private UMShareListener umShareListener;
    private android.os.Handler handler = new android.os.Handler();
    private boolean has_collect;
    private String title;
    private TextView btn_submit;
    private EditText inputComment;
    private PopupWindow popupWindow;

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initViews() {
        mWebView = (WebView) findViewById(R.id.wed_news_detail);
        //微信朋友圈
        mProgressBar = (ProgressBar) findViewById(R.id.progress_newsdetail);
        mWebView.loadUrl(this.url);

        //获取内容状态
        hodeNewsStatus();
    }

    private void hodeNewsStatus() {
        OkGo.<String>get(Constant.GET_NEWS_CONTENT_DETAIL_URL)
                .params("id", id).execute(new StringCallback() {
            @Override
            public void onSuccess(final Response<String> response) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (HttpStatusUtil.getStatus(response.body())) {
                            parseNewsContentData(response.body());
                            com.orhanobut.logger.Logger.d("收藏+++" + response.body().toString());
                        }
                    }
                }, 200);
            }
        });
    }

    //解析内容详情
    private void parseNewsContentData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            JSONObject data = jsonObject.getJSONObject("data");
            has_collect = data.getBoolean("has_collect");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initDatas() {
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
                llInputDiscuss.setVisibility(View.GONE);
//                llNewsDetailBigDiss.setVisibility(View.VISIBLE);
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
                StartThirdShare();
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
        }

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
                    popupWindow.dismiss();
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
                        if (response.body().code.equals("0")) {
                            asyncShowToast("评论成功");
                            llInputDiscuss.setVisibility(View.GONE);


                        }
                    }
                });
    }

    /**
     * 三方分享
     */
    private void StartThirdShare() {
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
                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN) {
                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.QZONE) {
                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            new ShareAction(NewsDetailActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        }
                    }
                }).open();

    }


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
            has_collect = (has_collect == true ? false : true);
            ivNewsDetailCollect.setBackgroundResource(has_collect == true ? R.drawable.collect : R.drawable.collect_normal);
            Toast.makeText(mContext, has_collect == true ? "收藏成功" : "取消收藏成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
