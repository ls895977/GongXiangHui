package com.qunxianghui.gxh.guide;

import android.support.v4.view.ViewPager;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.guide.view.IndicatorView;
import com.youth.banner.transformer.DepthPageTransformer;

public class GuideActivity extends BaseActivity {
    private static final String TAG = GuideActivity.class.getName();

    @Override
    protected int getLayoutId() {
        return R.layout.guide_activity;
    }

    @Override
    protected void initViews() {
        super.initViews();

        ViewPager viewPager = (ViewPager) findViewById(R.id.guide_vp_container);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setAdapter(new GuideViewPagerAdapter(getSupportFragmentManager()));

        IndicatorView indicatorView = (IndicatorView) findViewById(R.id.guide_iv_indicator_view);
        indicatorView.bindViewPager(viewPager);
    }
}
