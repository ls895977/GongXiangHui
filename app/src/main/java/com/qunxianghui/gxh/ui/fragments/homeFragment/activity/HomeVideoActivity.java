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
import com.qunxianghui.gxh.ui.fragments.homeFragment.fragments.HomeVideoListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class HomeVideoActivity extends BaseActivity {

    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.iv_video_more_columns)
    ImageView mIvVideoMoreColumns;
    @BindView(R.id.home_video_viewpager)
    ViewPager mHomeVideoViewpager;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    private String[] titles = new String[]{"实时", "搞笑", "体育", "娱乐"};
    private List<Fragment> fragments = new ArrayList<>();
    public final static int VIDEO_CHANNELREQUEST = 1; // 请求码
    public final static int VIDEO_CHANNELRESULT = 10; // 返回码

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
        MineTabViewPagerAdapter tabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mHomeVideoViewpager.setAdapter(tabViewPagerAdapter);
        mSlidingTabLayout.setViewPager(mHomeVideoViewpager);
        mHomeVideoViewpager.setOffscreenPageLimit(fragments.size() - 1);
    }

    @Override
    protected void initListeners() {
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

    @OnClick({R.id.iv_back, R.id.tv_search, R.id.tv_address, R.id.iv_video_more_columns})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                break;
            case R.id.tv_address:
                toActivity(LocationActivity.class);
                break;
            case R.id.iv_video_more_columns:
                toActivity(HomeVideoChannelActivity.class);
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
