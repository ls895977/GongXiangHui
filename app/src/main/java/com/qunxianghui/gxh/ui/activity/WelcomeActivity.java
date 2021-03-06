package com.qunxianghui.gxh.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.WelcomeAdvertBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.SystemUtil;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import kr.co.namee.permissiongen.PermissionGen;


/**
 * 欢迎界面 倒计时
 * Created by Administrator on 2018/3/26 0026.
 */

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.iv_welcomeadver)
    ImageView mIvWelcomeadver;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.ll_welcome_skip)
    LinearLayout mLlWelcomeSkip;

    private Animation animation;
    private int count = 5;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                mTextView.setText(String.valueOf(getCount()));
                handler.sendEmptyMessageDelayed(0, 1000);
                animation.reset();
                mTextView.startAnimation(animation);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);
        //textView.startAnimation(animation);
        handler.sendEmptyMessageDelayed(0, 1000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionGen.needPermission(WelcomeActivity.this, 105,
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }
            );
            boolean hasLocationPermission =
                    ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
            if (hasLocationPermission) {
                setImei();
            }
        } else {
            setImei();
        }
    }

    private void setImei() {
        String imei = SystemUtil.getIMEI(getApplicationContext());
        Set<String> set = new HashSet<>();
        set.add(imei);
        JPushInterface.setTags(WelcomeActivity.this, 1, set);
        OkGo.getInstance().getCommonHeaders().put("X-deviceId", imei);
    }

    private int getCount() {
        count--;
        if (count == 0) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (count == 3) {
            mLlWelcomeSkip.setVisibility(View.VISIBLE);
        }
        return count;
    }

    @Override
    protected void initData() {
        OkGo.getInstance().getCommonHeaders().put("X-accesstoken", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
        OkGo.getInstance().getCommonHeaders().put("X-deviceModel", SystemUtil.getSystemModel());
        OkGo.getInstance().getCommonHeaders().put("X-accesstoken", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
        String cityCode = SPUtils.getLocation("X-cityId");
        if (!TextUtils.isEmpty(cityCode)) {
            String areaId = SPUtils.getLocation("X-areaId");
            OkGo.getInstance().getCommonHeaders().put("X-cityId", cityCode);
            OkGo.getInstance().getCommonHeaders().put("X-areaId", areaId);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestWelcomeAdvert();
            }
        }, 1000);
    }

    /**
     * 请求网络广告
     */
    private void requestWelcomeAdvert() {
        OkGo.<String>get(Constant.WELCOM_ADVER_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        WelcomeAdvertBean welcomeAdvertBean = GsonUtil.parseJsonWithGson(response.body(), WelcomeAdvertBean.class);
                        if (welcomeAdvertBean.getCode() == 0) {
                            WelcomeAdvertBean.DataBean data = welcomeAdvertBean.getData();
                            String image = data.getImage();
                            if (mContext == null) {
                                return;
                            }
                            Glide.with(mContext)
                                    .load(image)
                                    .apply(new RequestOptions().placeholder(R.mipmap.icon_starpage)
                                            .error(R.mipmap.icon_starpage)
                                            .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL))
                                    .into(mIvWelcomeadver);
                        } else if (welcomeAdvertBean.getCode() == 1000) {
                            LoginMsgHelper.exitLogin();
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setImei();
        }
    }

    @Override
    protected void initListeners() {
        mLlWelcomeSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                toActivity(MainActivity.class);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

}
