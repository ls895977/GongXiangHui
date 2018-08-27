package com.qunxianghui.gxh.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.WelcomeAdvertBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

import butterknife.BindView;
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
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }
            );
        }
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

    /**
     * 请求网络广告
     */
    private void requestWelcomeAdvert() {
        OkGo.<WelcomeAdvertBean>get(Constant.WELCOM_ADVER_URL)
                .execute(new JsonCallback<WelcomeAdvertBean>() {
                    @Override
                    public void onSuccess(Response<WelcomeAdvertBean> response) {
                        WelcomeAdvertBean welcomeAdvertBean = response.body();
                        if (welcomeAdvertBean.getCode() == 0) {
                            WelcomeAdvertBean.DataBean data = welcomeAdvertBean.getData();
                            String image = data.getImage();
                            Glide.with(mContext)
                                    .load(image)
                                    .apply(new RequestOptions().placeholder(R.mipmap.icon_starpage)
                                            .error(R.mipmap.icon_starpage)
                                            .centerCrop())
                                    .into(mIvWelcomeadver);
                        }
                    }
                });
    }

    @Override
    protected void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestWelcomeAdvert();
            }
        }, 1000);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
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
        finish();
    }

}
