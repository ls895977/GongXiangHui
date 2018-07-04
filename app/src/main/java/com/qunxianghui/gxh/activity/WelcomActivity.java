package com.qunxianghui.gxh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.WelcomAdverBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 欢迎界面 倒计时
 * Created by Administrator on 2018/3/26 0026.
 */

public class WelcomActivity extends BaseActivity {
    @BindView(R.id.iv_welcomadver)
    ImageView ivWelcomadver;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.ll_welcom_skip)
    LinearLayout llWelcomSkip;
    private Animation animation;
    private int count = 4;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                textView.setText(getCount() + "");
                handler.sendEmptyMessageDelayed(0, 1000);
                animation.reset();
                textView.startAnimation(animation);
            }
            super.handleMessage(msg);
        }
    };
    private int getCount() {
        count--;
        if (count == 0) {
            toActivity(MainActivity.class);
            finish();
        }else if (count==2){
            llWelcomSkip.setVisibility(View.VISIBLE);
        }
        return count;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcom;
    }

    @Override
    protected void initViews() {
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);
        //textView.startAnimation(animation);
        handler.sendEmptyMessageDelayed(0, 1000);
    }
    /**
     * 请求网络广告
     */
    private void RequestWelcomAdver() {
        /**
         * 增加欢迎页广告
         */

        OkGo.<String>get(Constant.WELCOM_ADVER_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseWelcomData(response.body());
            }
        });
    }

    private void parseWelcomData(final String body) {
        final WelcomAdverBean welcomAdverBean = GsonUtils.jsonFromJson(body, WelcomAdverBean.class);
        if (welcomAdverBean.getCode() == 0) {
            final WelcomAdverBean.DataBean data = welcomAdverBean.getData();
            final String image = data.getImage();

            GlideApp.with(mContext).load(image).centerCrop()
                    .placeholder(R.mipmap.icon_starpage)
                    .error(R.mipmap.icon_starpage)
                    .into(ivWelcomadver);
        }
    }

    @Override
    protected void initDatas() {
     handler.postDelayed(new Runnable() {
         @Override
         public void run() {
             RequestWelcomAdver();
         }
     },1000);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        llWelcomSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(MainActivity.class);
                finish();
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
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
