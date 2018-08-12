package com.qunxianghui.gxh.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.EnterpriseMaterialFragment;
import com.qunxianghui.gxh.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class EnterpriseMaterialActivity extends BaseActivity {

    @BindView(R.id.segment_tab)
    SegmentTabLayout mSegmentTab;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.vp)
    NoScrollViewPager mVp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advert_template;
    }

    @Override
    protected void initViews() {
        mTvSave.setText("确定");
        mSegmentTab.setTabData(new String[]{"底部", "顶部"});
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new EnterpriseMaterialFragment());
        fragments.add(new EnterpriseMaterialFragment());
        mVp.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragments));
    }

    @Override
    protected void initListeners() {
        mSegmentTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                break;
        }
    }
}
