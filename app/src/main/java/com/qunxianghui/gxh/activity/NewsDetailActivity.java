package com.qunxianghui.gxh.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
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

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.SelfTestRecyclerviewAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.location.MyCollectBean;
import com.qunxianghui.gxh.config.Constant;

import com.qunxianghui.gxh.fragments.mineFragment.activity.AddAdverActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.HttpStatusUtil;

import com.qunxianghui.gxh.widget.TitleBuilder;

import com.sina.weibo.sdk.api.ImageObject;

import com.sina.weibo.sdk.share.WbShareHandler;




import org.json.JSONObject;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 新闻详情界面
 * Created by Administrator on 2018/4/9 0009.
 */

public class NewsDetailActivity extends BaseActivity implements View.OnClickListener{

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

    private Dialog dialog;
    private View alertView;
    private TextView tv_addAdver_share;
    private TextView tv_article_share;
    private TextView tv_bottom_alertdialog_cancle;


    private LinearLayout ll_shared_third_list;
    private LinearLayout ll_share_list;
    private ImageView iv_newsdetail_shared_weichat;
    private ImageView iv_newsdetail_wxshared_friendcircle;
    private ImageView iv_newsdetail_shared_sina;
    private ImageView iv_newsdetail_shared_qq;
    private Button bt_newsdetail_cancle_share;



    private Bundle params;

    private String url;


    private int uuid;
    private int id;
    private android.os.Handler handler = new android.os.Handler();
    private boolean has_collect;


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
        Intent intent = getIntent();
        intent.getStringExtra("url");
        mWebView.loadUrl(url);

        //获取内容状态
        hodeNewsStatus();

    }

    private void hodeNewsStatus() {
        OkGo.<String>post(Constant.GET_NEWS_CONTENT_DETAIL_URL)
                .params("id", id).execute(new StringCallback() {
            @Override
            public void onSuccess(final Response<String> response) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (HttpStatusUtil.getStatus(response.body().toString())) {
                            parseNewsContentData(response.body());

                            com.orhanobut.logger.Logger.d("收藏+++"+response.body().toString());
                        }

                    }
                }, 200);
            }
        });


    }

    //解析内容详情
    private void parseNewsContentData(String body) {
        try {
            final JSONObject jsonObject = new JSONObject(body);
            final JSONObject data = jsonObject.getJSONObject("data");
            has_collect = data.getBoolean("has_collect");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
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
        ivNewsDetailCollect.setOnClickListener(this);
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
                //判断用户单机的是那个超链接
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
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
                intent = new Intent(mContext, AddAdverActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
                dialog.dismiss();


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

                intent = new Intent(mContext, AddAdverActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);

                break;

            case R.id.iv_newsdetail_shared_qq:
                asyncShowToast("点击了分享QQ");

                dialog.dismiss();
                break;
            case R.id.iv_newsdetail_shared_weichat:

                asyncShowToast("点击了分享微信");


                dialog.dismiss();
                break;

            case R.id.iv_newsdetail_wxshared_friendcircle:
                //分享到朋友圈

                break;
            case R.id.iv_newsdetail_shared_sina:
                asyncShowToast("点击分享了新浪微博");

                dialog.dismiss();
                break;
            case R.id.bt_newsdetail_cancle_share:
                dialog.dismiss();
                break;
            case R.id.iv_news_detail_collect:

                CollectDataList(uuid);


                break;
        }

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
        com.orhanobut.logger.Logger.d("收藏的详细信息---" + body.toString());

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
