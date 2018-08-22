package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.activity.WelcomeActivity;
import com.qunxianghui.gxh.utils.SPUtils;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        boolean isFirstUse = SPUtils.getBoolean("isFirstUse");
        if (isFirstUse) {
            toActivity(GuidActivity.class);
        } else {
            toActivity(WelcomeActivity.class);
        }
        finish();
    }
}
