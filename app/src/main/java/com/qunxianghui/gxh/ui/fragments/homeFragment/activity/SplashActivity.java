package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.content.Intent;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.qiyukf.nimlib.sdk.NimIntent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
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
            String areaId = SPUtils.getLocation("x-areaId");
            OkGo.getInstance().getCommonHeaders().put("x-cityId", cityCode);
            OkGo.getInstance().getCommonHeaders().put("x-areaId", areaId);
        }


        Intent intent = getIntent();
        if (intent.hasExtra(NimIntent.EXTRA_NOTIFY_CONTENT)) {
            // 打开客服窗口
            Unicorn.openServiceActivity(mContext, "群享汇客服", new ConsultSource("https://www.baidu.com/","客服","ssss"));
            // 最好将intent清掉，以免从堆栈恢复时又打开客服窗口
            setIntent(new Intent());
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
