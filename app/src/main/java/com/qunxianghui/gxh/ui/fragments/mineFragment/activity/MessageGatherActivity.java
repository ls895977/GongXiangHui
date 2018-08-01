package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MineFancesFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.fragment.MyIssurePostFragment;
import com.qunxianghui.gxh.widget.NoScrollViewPager;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class MessageGatherActivity extends BaseActivity {
    private String[] titles = new String[]{"我的帖子", "我的关注", "我的粉丝"};
    private List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.mine_MessageGather_Tablayout_common)
    TabLayout mineMessageGatherTablayoutCommon;
    @BindView(R.id.mine_messageGather_viewpager)
    NoScrollViewPager mineMessageGatherViewpager;
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_gather;
    }
    @Override
    protected void initViews() {
        for (String tab : titles) {
            mineMessageGatherTablayoutCommon.addTab(mineMessageGatherTablayoutCommon.newTab().setText(tab));
        }
    }
    @Override
    protected void initData() {
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("信息汇总");


        fragments.add(new MyIssurePostFragment());
        fragments.add(new MineFancesFragment());
        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mineMessageGatherViewpager.setAdapter(mineTabViewPagerAdapter);
        mineMessageGatherViewpager.setOffscreenPageLimit(3);
        mineMessageGatherTablayoutCommon.setupWithViewPager(mineMessageGatherViewpager);
        mineMessageGatherViewpager.setScroll(true);
    }
}
