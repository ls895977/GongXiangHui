package com.qunxianghui.gxh.guide;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.lzy.okgo.OkGo;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.guide.view.IndicatorView;
import com.qunxianghui.gxh.utils.SystemUtil;
import com.youth.banner.transformer.DepthPageTransformer;

import kr.co.namee.permissiongen.PermissionGen;

public class GuideActivity extends BaseActivity {
    private static final String TAG = GuideActivity.class.getName();

    @Override
    protected int getLayoutId() {
        return R.layout.guide_activity;
    }

    @Override
    protected void initViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionGen.needPermission(GuideActivity.this, 105,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                    }
            );

            boolean hasLocationPermission =
                    ContextCompat.checkSelfPermission(GuideActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
            if (hasLocationPermission) {
                OkGo.getInstance().getCommonHeaders().put("X-deviceId", SystemUtil.getIMEI(getApplicationContext()));
            }
        } else {
            OkGo.getInstance().getCommonHeaders().put("X-deviceId", SystemUtil.getIMEI(getApplicationContext()));
        }

        ViewPager viewPager = findViewById(R.id.guide_vp_container);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setAdapter(new GuideViewPagerAdapter(getSupportFragmentManager()));

        IndicatorView indicatorView = findViewById(R.id.guide_iv_indicator_view);
        indicatorView.bindViewPager(viewPager);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            OkGo.getInstance().getCommonHeaders().put("X-deviceId", SystemUtil.getIMEI(getApplicationContext()));
        }
    }
}
