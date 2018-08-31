package com.qunxianghui.gxh.guide;

import android.Manifest;
import android.os.Build;
import android.support.v4.view.ViewPager;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.guide.view.IndicatorView;
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
                            Manifest.permission.ACCESS_FINE_LOCATION,
                    }
            );
        }

        ViewPager viewPager = findViewById(R.id.guide_vp_container);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setAdapter(new GuideViewPagerAdapter(getSupportFragmentManager()));

        IndicatorView indicatorView = findViewById(R.id.guide_iv_indicator_view);
        indicatorView.bindViewPager(viewPager);
    }
}
