package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.activity.WelcomeActivity;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.SystemUtil;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        boolean isFirstUse = SPUtils.getBoolean("isFirstUse");
        OkGo.getInstance().getCommonHeaders().put("X-deviceModel", SystemUtil.getSystemModel());
        OkGo.getInstance().getCommonHeaders().put("X-deviceId", SystemUtil.getIMEI(getApplicationContext()));

        String cityCode = SPUtils.getLocation("X-cityId");
        if (!TextUtils.isEmpty(cityCode)) {
            String areaId = SPUtils.getLocation("X-areaId");
            OkGo.getInstance().getCommonHeaders().put("X-cityId", cityCode);
            OkGo.getInstance().getCommonHeaders().put("X-areaId", areaId);
        }
        if (isFirstUse) {
            toActivity(GuidActivity.class);
        } else {
            toActivity(WelcomeActivity.class);
        }
        finish();
    }
}
