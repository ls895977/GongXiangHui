package com.gongxianghui.fragments.homeFragment;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.gongxianghui.R;
import com.gongxianghui.activity.ScanActivity;
import com.gongxianghui.adapter.homeAdapter.MyViewPagerAdapter;
import com.gongxianghui.base.BaseFragment;
import com.gongxianghui.fragments.homeFragment.activity.BaoLiaoActivity;
import com.gongxianghui.fragments.homeFragment.activity.HomeAddTabActivity;
import com.gongxianghui.fragments.homeFragment.activity.SearchActivity;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HomeFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, View.OnClickListener {

    @BindView(R.id.ib_home_camera)
    ImageButton ibHomeCamera;
    @BindView(R.id.ib_home_search)
    ImageButton ibHomeSearch;
    @BindView(R.id.ib_home_scan)
    ImageButton ibHomeScan;
    @BindView(R.id.iv_homepoint_add)
    ImageView ivHomepointAdd;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //TabLayout标签
    private String[] titles = new String[]{"热点",
            "本地", "社会", "教育", "健康"};
    private List<Fragment> fragments = new ArrayList<>();
    private MyViewPagerAdapter viewPagerAdapter;

    @BindView(R.id.home_tab_layout)
    TabLayout homeTabLayout;
    @BindView(R.id.home_view_pager)
    ViewPager homeViewPager;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.home_layout;
    }

    @Override
    public void initDatas() {
        homeTabLayout.setOnTabSelectedListener(this);
        fragments.add(new HotPointFragment());
        fragments.add(new HotPointFragment());
        fragments.add(new HotPointFragment());
        fragments.add(new HotPointFragment());
        fragments.add(new HotPointFragment());


        viewPagerAdapter = new MyViewPagerAdapter(mActivity.getSupportFragmentManager(), titles, fragments);
        homeViewPager.setAdapter(viewPagerAdapter);
        homeTabLayout.setupWithViewPager(homeViewPager);


    }

    @Override
    public void initViews(View view) {

        homeTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (String tab : titles) {
            homeTabLayout.addTab(homeTabLayout.newTab().setText(tab));
        }

    }

    @Override
    protected void initListeners() {
        ibHomeCamera.setOnClickListener(this);
        ibHomeSearch.setOnClickListener(this);
        ibHomeScan.setOnClickListener(this);
        ivHomepointAdd.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        homeViewPager.setCurrentItem(tab.getPosition());
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
            case R.id.ib_home_camera:            //爆料
                toActivity(BaoLiaoActivity.class);
                break;
            case R.id.ib_home_scan:            //扫描二维码

                toActivity(ScanActivity.class);


                break;
            case R.id.ib_home_search:          //搜索
                toActivity(SearchActivity.class);
                break;
            case R.id.iv_homepoint_add:
                toActivity(HomeAddTabActivity.class);
                break;
        }
    }


}
