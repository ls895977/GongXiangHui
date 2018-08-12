package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.PagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;

import butterknife.BindView;

public class EnterpriseMaterialFragment extends BaseFragment {

    @BindView(R.id.tab)
    SlidingTabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;

    private String[] mTitles = new String[]{"大图通栏","名片","通栏","二维码","QQ","店铺","图文"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_enterprise_materia;
    }

    @Override
    public void initViews(View view) {
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), mTitles);
        Bundle bundle;
        Fragment fragment;
        for (int i = 1; i <= mTitles.length; i++) {
            bundle = new Bundle();
            fragment = new EnterpriseMateriaItemFragment();
            bundle.putInt("type", i);
            fragment.setArguments(bundle);
            adapter.addFragment(fragment);
        }
        mVp.setAdapter(adapter);
        mTab.setViewPager(mVp);
    }
}
