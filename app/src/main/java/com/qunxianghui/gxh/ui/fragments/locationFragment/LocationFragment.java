package com.qunxianghui.gxh.ui.fragments.locationFragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.AbleNewSearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class LocationFragment extends BaseFragment {

    @BindView(R.id.tv_localcircle_location)
    TextView mTvLocalcircleLocation;
    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.local_view_pager)
    ViewPager mLocalViewPager;

    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;
    private String[] titles = new String[]{"实时", "搞笑", "体育", "娱乐"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_location;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        int height = mSlidingTabLayout.getHeight();
    }

    @Override
    public void initData() {
        fragments.add(new LocationDetailFragment());
        fragments.add(new LocationDetailFragment());
        fragments.add(new LocationDetailFragment());
        fragments.add(new LocationDetailFragment());

        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getChildFragmentManager(), fragments, titles);
        mLocalViewPager.setAdapter(mineTabViewPagerAdapter);
        mSlidingTabLayout.setViewPager(mLocalViewPager);
        mLocalViewPager.setOffscreenPageLimit(fragments.size());
    }

    @Override
    protected void initListeners() {
        mSlidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mLocalViewPager.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    String city = getActivity().getSharedPreferences("location", MODE_PRIVATE).getString("currcity", "");
                    mTvLocalcircleLocation.setText(city);
                }
                break;
        }
    }

    @OnClick({R.id.tv_localcircle_location, R.id.iv_more_columns})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_localcircle_location:
                startActivityForResult(new Intent(mActivity, AbleNewSearchActivity.class), CITY_SELECT_RESULT_FRAG);
                break;
            case R.id.iv_more_columns:
                break;
        }
    }
}

