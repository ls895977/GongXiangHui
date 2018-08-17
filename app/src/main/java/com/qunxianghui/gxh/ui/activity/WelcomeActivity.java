package com.qunxianghui.gxh.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.WelcomeAdvertBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import butterknife.BindView;


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
        OkGo.<String>get(Constant.WELCOM_ADVER_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                WelcomeAdvertBean welcomeAdvertBean = GsonUtils.jsonFromJson(response.body(), WelcomeAdvertBean.class);
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
