package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.fragments.homeFragment.fragments.HomeVideoListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class HomeVideoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_home_video_back)
    ImageView ivHomeVideoBack;
    @BindView(R.id.tv_home_video_issue)
    TextView tvHomeVideoIssue;
    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.iv_more_columns)
    ImageView mIvMoreColumns;
    @BindView(R.id.home_video_viewpager)
    ViewPager mHomeVideoViewpager;
    private String[] titles = new String[]{"实时", "搞笑", "体育", "娱乐"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter tabViewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_video;
    }

    @Override
    protected void initViews() {
        fragments.add(new HomeVideoListFragment());
        fragments.add(new HomeVideoListFragment());
        fragments.add(new HomeVideoListFragment());
        fragments.add(new HomeVideoListFragment());
        tabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mHomeVideoViewpager.setAdapter(tabViewPagerAdapter);
        mSlidingTabLayout.setViewPager(mHomeVideoViewpager);
        mHomeVideoViewpager.setOffscreenPageLimit(fragments.size() - 1);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        ivHomeVideoBack.setOnClickListener(this);
        tvHomeVideoIssue.setOnClickListener(this);
        mIvMoreColumns.setOnClickListener(this);
        mSlidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mHomeVideoViewpager.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
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
            case R.id.iv_more_columns:
                asyncShowToast("点击了分类条目");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
