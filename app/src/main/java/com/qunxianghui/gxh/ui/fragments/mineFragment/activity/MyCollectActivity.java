package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MineCollectVideoFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MineCommonFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class MyCollectActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener {

    @BindView(R.id.mine_tablayout_common)
    TabLayout mineTablayoutCommon;
    @BindView(R.id.mine_common_viewpager)
    ViewPager mineCommonViewpager;
    @BindView(R.id.iv_myCollect_back)
    ImageView ivMyCollectBack;
    private String[] titles = new String[]{"资讯", "视频"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter tabViewPagerAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity__mine_mycollect;
    }
    @Override
    protected void initViews() {
        //设置tabLayout的一个显示方式
        mineTablayoutCommon.setTabMode(TabLayout.MODE_FIXED);
        //循环注入标签
        for (String tab : titles) {
            mineTablayoutCommon.addTab(mineTablayoutCommon.newTab().setText(tab));
        }
    }

    @Override
    protected void initDatas() {
        //设置tablayout的点击事件
        mineTablayoutCommon.setOnTabSelectedListener(this);
        ivMyCollectBack.setOnClickListener(this);
        fragments.add(new MineCommonFragment());
        fragments.add(new MineCollectVideoFragment());

        tabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mineCommonViewpager.setAdapter(tabViewPagerAdapter);
        mineCommonViewpager.setOffscreenPageLimit(2);
        mineTablayoutCommon.setupWithViewPager(mineCommonViewpager);
        mineTablayoutCommon.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mineTablayoutCommon,60,60);
            }
        });

    }

    private void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip= tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mineCommonViewpager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_myCollect_back:
                finish();
                break;

        }
    }
}
