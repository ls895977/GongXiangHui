package com.qunxianghui.gxh.guide;

import android.support.v4.view.ViewPager;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.youth.banner.transformer.DepthPageTransformer;

public class GuideActivity extends BaseActivity {
    private static final String TAG = GuideActivity.class.getName();
    private ViewPager vpContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.guide_activity;
    }

    @Override
    protected void initViews() {
        super.initViews();

        vpContainer = (ViewPager) findViewById(R.id.guide_vp_container);
        vpContainer.setPageTransformer(true, new DepthPageTransformer());
        vpContainer.setAdapter(new GuideViewPagerAdapter(getSupportFragmentManager()));
    }
}
