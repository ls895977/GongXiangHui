package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MineMessageFollowFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MineMessageFragment;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class MineMessageActivity extends BaseActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {
    @BindView(R.id.mine_myMessaageTablayout_common)
    TabLayout mineMyMessaageTablayoutCommon;
    @BindView(R.id.mine_mymessage_viewpager)
    ViewPager mineMymessageViewpager;
    private String[] titles = new String[]{"评论我的", "我的评论"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_message;
    }

    @Override
    protected void initViews() {
        //设置tablalayout的一个显示方式
        mineMyMessaageTablayoutCommon.setTabMode(TabLayout.MODE_FIXED);
        //循环注入
        for (String tab : titles) {
            mineMyMessaageTablayoutCommon.addTab(mineMyMessaageTablayoutCommon.newTab().setText(tab));
        }

    }

    @Override
    protected void initData() {
        new TitleBuilder(this).setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("我的消息");

//设置tablayout的点击事件
        mineMyMessaageTablayoutCommon.setOnTabSelectedListener(this);
        fragments.add(new MineMessageFragment());
        fragments.add(new MineMessageFollowFragment());
        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mineMymessageViewpager.setAdapter(mineTabViewPagerAdapter);
        mineMymessageViewpager.setOffscreenPageLimit(fragments.size());
        mineMyMessaageTablayoutCommon.setupWithViewPager(mineMymessageViewpager);

        mineMyMessaageTablayoutCommon.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mineMyMessaageTablayoutCommon, 60, 60);
            }
        });
    }

    private void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
