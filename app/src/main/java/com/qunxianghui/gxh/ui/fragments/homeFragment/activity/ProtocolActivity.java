package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.CompanySetActivity;
import com.qunxianghui.gxh.utils.FileUtils;
import com.qunxianghui.gxh.utils.NetWorkUtil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.umeng.commonsdk.statistics.common.MLog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class ProtocolActivity extends BaseActivity implements View.OnClickListener {

    final Activity mact = this;
    @BindView(R.id.ll_protocol_main)
    RelativeLayout llProtocolMain;
    @BindView(R.id.iv_webback)
    ImageView ivWebback;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_newsdetail_issue)
    TextView tvNewsdetailIssue;
    @BindView(R.id.rl_protocol_title)
    RelativeLayout mRlProtocolTitle;
    @BindView(R.id.rl_news_detail_notnet)
    RelativeLayout rlNewsDetailNotnet;
    @BindView(R.id.tv_news_detail_notnet)
    TextView tvNewsDetailNotnet;
    @BindView(R.id.web_protocol_detail)
    WebView webProtocolDetail;


    private StringBuffer mBuffer;
    private Dialog mMLoadingDialog;

    //5.0以下使用
    private ValueCallback mUploadMessage;
    // 5.0及以上使用
    private ValueCallback<Uri[]> mUploadMessageAboveL;
    //图片
    private final static int FILE_CHOOSER_RESULT_CODE = 135;
    //拍照图片路径
    private String mCameraFielPath;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void initViews() {
        mMLoadingDialog = createLoadingDialog(ProtocolActivity.this, "加载中...");
        mMLoadingDialog.show();
    }

    private Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);//得到加载view
        LinearLayout layout = v.findViewById(R.id.dialog_view);//加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = v.findViewById(R.id.dialog_img);
        TextView tipTextView = v.findViewById(R.id.tipTextView);// 提示文字
        //加载动画
        final Animation animation = AnimationUtils.loadAnimation(context, R.anim.load_animation);
        //使用imageView显示动画
        spaceshipImage.startAnimation(animation);
        tipTextView.setText(msg);  //设置加载信息
        final Dialog loadingDialog = new Dialog(context);
        loadingDialog.setCancelable(true);  //不可以用返回键 取消
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)); //设置布局
        return loadingDialog;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initData() {
        Intent intent = getIntent();
        final String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        String mToken = intent.getStringExtra("token");
        String arera = intent.getStringExtra("arera");
        int tag = intent.getIntExtra("tag", 0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, 150, 0, 0);
        if (tag == 1) {
            mBuffer = new StringBuffer(url);
            try {
                mBuffer.append("?token=" + mToken).append("&shi_id=" + SPUtils.getLocation("X-cityId")).append("&qu_id=" + SPUtils.getLocation("X-areaId")).append("&qu=" + URLDecoder.decode(SPUtils.getLocation("currcity"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.setMargins(0, 60, 0, 0);
            mRlProtocolTitle.setVisibility(View.GONE);

        } else if (tag == 2) {
            params.setMargins(0, 60, 0, 0);
            mBuffer = new StringBuffer(url);
        } else if (tag == 3) {
            params.setMargins(0, 60, 0, 0);
            mBuffer = new StringBuffer(url);
            mRlProtocolTitle.setVisibility(View.GONE);
        }
        tvTitle.setText(title);

        WebSettings settings = webProtocolDetail.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDisplayZoomControls(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAppCacheEnabled(true);
        settings.setUseWideViewPort(true);
        webProtocolDetail.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    mMLoadingDialog.dismiss();
                }
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                mUploadMessage = valueCallback;
                openImageChooser();
            }

            // For Android  >= 3.0
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                mUploadMessage = valueCallback;
                openImageChooser();
            }

            //For Android  >= 4.1
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                mUploadMessage = valueCallback;
                openImageChooser();
            }

            // For Android >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                mUploadMessageAboveL = filePathCallback;
                openImageChooser();
                return true;
            }
        });
        webProtocolDetail.setWebViewClient(new WebViewClient() {
            private Intent mMIntent;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                return isCall(url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Logger.d("shouldOverrideUrlLoading--->:" + url);
                return isCall(url);
            }

            private boolean isCall(String url) {
                if (url.contains("tel:1516715042")) {
                    ProtocolActivity.this.finish();
                } else if (url.contains("tel")) {
                    String mobile = url.substring(url.lastIndexOf("/") + 1);
                    mMIntent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse(mobile);
                    mMIntent.setData(data);
                    if (ActivityCompat.checkSelfPermission(ProtocolActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(mMIntent);
                    } else {
                        //申请权限
                        ActivityCompat.requestPermissions(ProtocolActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }

        });
        webProtocolDetail.loadUrl(String.valueOf(mBuffer));
    }


    @Override
    protected void onResume() {
        super.onResume();
        webProtocolDetail.resumeTimers();
        webProtocolDetail.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webProtocolDetail.onPause();
            webProtocolDetail.pauseTimers(); // 暂停网页中正在播放的视频
        }

    }


    @Override
    protected void initListeners() {
        super.initListeners();
        ivWebback.setOnClickListener(this);
        tvNewsdetailIssue.setOnClickListener(this);
        if (!NetWorkUtil.isNetWorkAvailable(ProtocolActivity.this)) {
            webProtocolDetail.setVisibility(View.GONE);
            rlNewsDetailNotnet.setVisibility(View.VISIBLE);
            mRlProtocolTitle.setVisibility(View.VISIBLE);
            tvTitle.setText("加载失败");
            ivWebback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        } else {
            tvNewsDetailNotnet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    webProtocolDetail.reload();
                    webProtocolDetail.setVisibility(View.GONE);
                    webProtocolDetail.setWebChromeClient(new WebChromeClient() {
                        @Override
                        public void onProgressChanged(WebView view, int newProgress) {
                            if (newProgress >= 80) {
                                rlNewsDetailNotnet.setVisibility(View.GONE);
                            }
                        }
                    });

                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_webback:
                finish();
                break;
            case R.id.tv_newsdetail_issue:
                toActivity(CompanySetActivity.class);
                break;
        }
    }

    private void openImageChooser() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = FileUtils.createTempImageFile();
            } catch (Exception ex) {
                MLog.e(TAG, "Image file creation failed", ex);
            }
            if (photoFile != null) {
                mCameraFielPath = photoFile.getAbsolutePath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            } else {
                takePictureIntent = null;
            }
        }

        Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        imageIntent.addCategory(Intent.CATEGORY_OPENABLE);
        imageIntent.setType("image/*");
        Intent[] intentArray;
        if (takePictureIntent != null) {
            intentArray = new Intent[]{takePictureIntent};
        } else {
            intentArray = new Intent[0];
        }

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, imageIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);

        startActivityForResult(chooserIntent, FILE_CHOOSER_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null == mUploadMessage && null == mUploadMessageAboveL) return;
        if (resultCode != RESULT_OK) {
            if (mUploadMessageAboveL != null) {
                mUploadMessageAboveL.onReceiveValue(null);
                mUploadMessageAboveL = null;
            }
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
            }
            return;
        }
        Uri result = null;
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null != data && null != data.getData()) {
                result = data.getData();
            }
            if (result == null && FileUtils.isExist(mCameraFielPath)) {
                result = Uri.fromFile(new File(mCameraFielPath));
            }
            if (mUploadMessageAboveL != null) {
                onActivityResultAboveL(data, result);
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(Intent intent, Uri uri) {
        Uri[] results = null;
        if (intent != null) {
            String dataString = intent.getDataString();
            ClipData clipData = intent.getClipData();
            if (clipData != null) {
                results = new Uri[clipData.getItemCount()];
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    results[i] = item.getUri();
                }
            }
            if (dataString != null)
                results = new Uri[]{Uri.parse(dataString)};
        } else {
            if (uri != null) {
                results = new Uri[]{uri};
            }
        }

        mUploadMessageAboveL.onReceiveValue(results);
        mUploadMessageAboveL = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webProtocolDetail.canGoBack()) {
            webProtocolDetail.goBack();
        } else {
            finish();
        }
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
        if (webProtocolDetail != null) {
            webProtocolDetail.clearHistory();
            webProtocolDetail.clearCache(true);
            webProtocolDetail.loadUrl("about:blank");
            webProtocolDetail.freeMemory();
            webProtocolDetail.pauseTimers();
            webProtocolDetail.destroy();
        }


    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();

    }
}
