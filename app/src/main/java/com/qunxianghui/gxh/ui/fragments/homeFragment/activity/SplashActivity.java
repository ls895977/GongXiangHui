package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.guide.GuideActivity;
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
        OkGo.getInstance().getCommonHeaders().put("X-accesstoken", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
        OkGo.getInstance().getCommonHeaders().put("X-deviceModel", SystemUtil.getSystemModel());
        String cityCode = SPUtils.getLocation("X-cityId");

        if (!TextUtils.isEmpty(cityCode)) {
            String areaId = SPUtils.getLocation("X-areaId");
            OkGo.getInstance().getCommonHeaders().put("X-cityId", cityCode);
            OkGo.getInstance().getCommonHeaders().put("X-areaId", areaId);
        }

    }

    @Override
    protected void initData() {
        boolean isFirstUse = SPUtils.getBoolean("isFirstUse");
        if (isFirstUse) {
            toActivity(GuideActivity.class);
        } else {
            toActivity(WelcomeActivity.class);
        }
        finish();
    }


}
