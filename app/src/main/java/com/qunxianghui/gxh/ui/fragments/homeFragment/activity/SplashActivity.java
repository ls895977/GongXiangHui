package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.content.SharedPreferences;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.activity.WelcomeActivity;

public class SplashActivity extends BaseActivity {


    //是否是第一次使用
    private boolean isFirstUse;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        SharedPreferences preferences = getSharedPreferences("isFirstUse", MODE_PRIVATE);
        isFirstUse = preferences.getBoolean("isFirstUse", true);
        if (isFirstUse){
            toActivity(GuidActivity.class);
        }else {
            toActivity(WelcomeActivity.class);
        }
        finish();
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("isFirstUse",false);
        edit.commit();

    }
}
