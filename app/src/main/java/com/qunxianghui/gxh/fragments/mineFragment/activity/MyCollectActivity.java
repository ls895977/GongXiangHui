package com.qunxianghui.gxh.fragments.mineFragment.activity;

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
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineCollectPostFrament;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineCommonFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @BindView(R.id.tv_mycollect_edit)
    TextView tvMycollectEdit;


    private String[] titles = new String[]{"资讯", "视频", "帖子"};
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
        tvMycollectEdit.setOnClickListener(this);

        fragments.add(new MineCommonFragment());
        fragments.add(new MineCommonFragment());
        fragments.add(new MineCollectPostFrament());

        tabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mineCommonViewpager.setAdapter(tabViewPagerAdapter);
        mineTablayoutCommon.setupWithViewPager(mineCommonViewpager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
            case R.id.tv_mycollect_edit:
                break;
        }
    }
}
