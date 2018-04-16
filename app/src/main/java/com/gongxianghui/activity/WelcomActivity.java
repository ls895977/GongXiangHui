package com.gongxianghui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;



/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class WelcomActivity extends BaseActivity {
    // 声明控件对象
    private TextView textView;
    private Animation animation;
    private int count = 3;
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {

        if(msg.what==0){
            textView.setText(getCount()+"");
            handler.sendEmptyMessageDelayed(0,1000);
            animation.reset();
            textView.startAnimation(animation);
        }

        super.handleMessage(msg);
    }
};

    private int getCount() {
        count--;
        if(count==0){
            Intent intent = new Intent(this, MainActivity.class);
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
