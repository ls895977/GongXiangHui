package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.fragments.homeFragment.fragments.HomeVideoListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class HomeVideoActivity extends BaseActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {

    @BindView(R.id.iv_home_video_back)
    ImageView ivHomeVideoBack;
    @BindView(R.id.tv_home_video_issue)
    TextView tvHomeVideoIssue;
    @BindView(R.id.homevideo_tablayout)
    TabLayout homevideoTablayout;
    @BindView(R.id.home_video_viewpager)
    ViewPager homeVideoViewpager;
    private String[] titles = new String[]{"实时", "搞笑", "体育", "娱乐"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter tabViewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_video;
    }

    @Override
    protected void initViews() {
        //设置tabLayout的一个显示方式
        homevideoTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //循环注入标签
        for (String tab : titles) {
            homevideoTablayout.addTab(homevideoTablayout.newTab().setText(tab));
        }
    }

    @Override
    protected void initData() {
        homevideoTablayout.setOnTabSelectedListener(this);
        fragments.add(new HomeVideoListFragment());
        fragments.add(new HomeVideoListFragment());
        fragments.add(new HomeVideoListFragment());
        fragments.add(new HomeVideoListFragment());

        tabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        homeVideoViewpager.setAdapter(tabViewPagerAdapter);
        homeVideoViewpager.setOffscreenPageLimit(fragments.size());
        homevideoTablayout.setupWithViewPager(homeVideoViewpager);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ivHomeVideoBack.setOnClickListener(this);
        tvHomeVideoIssue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_video_back:
                finish();
                break;
            case R.id.tv_home_video_issue:
                toActivity(LocationActivity.class);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        homeVideoViewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
