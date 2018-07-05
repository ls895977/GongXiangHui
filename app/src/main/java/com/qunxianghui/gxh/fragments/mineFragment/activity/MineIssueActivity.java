package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MyIssueDiscloseFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MyIssurePostFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MyIssureVideoFragment;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class MineIssueActivity extends BaseActivity {
    @BindView(R.id.mine_MyIssureTablayout_common)
    TabLayout mineMyIssureTablayoutCommon;
    @BindView(R.id.mine_MyIssure_viewpager)
    ViewPager mineMyIssureViewpager;
    private String[] titles = new String[]{"爆料", "视频", "帖子"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_issue;
    }

    @Override
    protected void initViews() {
        //设置tablayout的一个显示方式
        mineMyIssureTablayoutCommon.setTabMode(TabLayout.MODE_FIXED);
        for (String tab : titles) {
            mineMyIssureTablayoutCommon.addTab(mineMyIssureTablayoutCommon.newTab().setText(tab));
        }


        int position = getIntent().getIntExtra("index", 0);
    
    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("我的发布");




        fragments.add(new MyIssueDiscloseFragment());
        fragments.add(new MyIssureVideoFragment());
        fragments.add(new MyIssurePostFragment());

        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mineMyIssureViewpager.setAdapter(mineTabViewPagerAdapter);
        mineMyIssureViewpager.setOffscreenPageLimit(fragments.size());
        mineMyIssureTablayoutCommon.setupWithViewPager(mineMyIssureViewpager);
    }

}
