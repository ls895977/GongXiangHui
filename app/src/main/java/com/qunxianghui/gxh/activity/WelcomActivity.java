package com.qunxianghui.gxh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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

    // 声明控件对象
    private TextView textView;
    private Animation animation;
    private int count = 1;
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
            Intent intent = new Intent(this, WelcomAdverActivity.class);
            startActivity(intent);
            finish();
        }


        return count;
    }

    @Override
    protected int getLayoutId() {

        return R.layout.activity_welcom;
    }

    @Override
    protected void initViews() {
        // 初始化控件对象
        textView = (TextView) findViewById(R.id.textView);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_text);
        //textView.startAnimation(animation);
        handler.sendEmptyMessageDelayed(0, 1000);

    }

    @Override
    protected void initDatas() {



    }


}
