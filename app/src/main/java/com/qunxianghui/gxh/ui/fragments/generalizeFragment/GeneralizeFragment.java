package com.qunxianghui.gxh.ui.fragments.generalizeFragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments.GeneraCompanyFragment;
import com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments.GeneraPersonalFragment;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class GeneralizeFragment extends BaseFragment implements Observer{

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
        EventManager.getInstance().addObserver(this);
        mSegmentTab.setTabData(mTitles);
    }
    @SuppressLint("NewApi")
    @Override
    protected void setStatusBarColor(){
        Window window = mActivity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.style_status_color));
    }

    @Override
    public void initData() {
        if (mLlGenera == null) return;
        if (SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
            mSegmentTab.setCurrentTab(0);
            mLlGenera.setVisibility(View.VISIBLE);
            mVp.setVisibility(View.VISIBLE);
            mViewPerson.setVisibility(View.GONE);
            mRlPerson.setVisibility(View.GONE);
            final List<Fragment> fragments = new ArrayList<>();
            fragments.add(new GeneraPersonalFragment());
            fragments.add(new GeneraCompanyFragment());

            MainViewPagerAdapter adapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments);
            mVp.setAdapter(adapter);
            mVp.setOffscreenPageLimit(fragments.size() - 1);
        } else {
            mLlGenera.setVisibility(View.GONE);
            mVp.setVisibility(View.GONE);
            mViewPerson.setVisibility(View.VISIBLE);
            mRlPerson.setVisibility(View.VISIBLE);
            getChildFragmentManager().beginTransaction().add(R.id.rl_person, new GeneraPersonalFragment()).commit();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventManager.getInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String && "company".equals(arg)) {
            initData();
        }
    }
}