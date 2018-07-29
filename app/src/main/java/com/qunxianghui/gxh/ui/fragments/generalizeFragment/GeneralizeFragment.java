package com.qunxianghui.gxh.ui.fragments.generalizeFragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments.GeneraCompanyFragment;
import com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments.GeneraPersonalFragment;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class GeneralizeFragment extends BaseFragment {

    @BindView(R.id.view_person)
    View mViewPerson;
    @BindView(R.id.rl_genera)
    View mLlGenera;
    @BindView(R.id.rl_person)
    View mRlPerson;
    @BindView(R.id.segment_tab)
    SegmentTabLayout mSegmentTab;
    @BindView(R.id.vp)
    ViewPager mVp;

    private String[] mTitles = {"个人", "企业"};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_generalizes;
    }

    @Override
    public void initViews(View view) {
        mSegmentTab.setTabData(mTitles);
    }

    @Override
    public void initData() {
        if (LoginMsgHelper.isLogin()) {
            if (SPUtils.getBoolean(SpConstant.IS_COMPANY, false)) {
                mLlGenera.setVisibility(View.VISIBLE);
                mVp.setVisibility(View.VISIBLE);
                mViewPerson.setVisibility(View.GONE);
                mRlPerson.setVisibility(View.GONE);
                final List<Fragment> fragments = new ArrayList<>();
                fragments.add(new GeneraPersonalFragment());
                fragments.add(new GeneraCompanyFragment());

                MainViewPagerAdapter adapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments);
                mVp.setAdapter(adapter);
                /**增加缓存页面的数量*/
                mVp.setOffscreenPageLimit(fragments.size() - 1);
                /**默认显示第一个选项卡*/
            } else {
                mLlGenera.setVisibility(View.GONE);
                mVp.setVisibility(View.GONE);
                mViewPerson.setVisibility(View.VISIBLE);
                mRlPerson.setVisibility(View.VISIBLE);
                getChildFragmentManager().beginTransaction().add(R.id.rl_person, new GeneraPersonalFragment()).commit();
            }
        }
    }

    @Override
    protected void initListeners() {
        mSegmentTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mSegmentTab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}