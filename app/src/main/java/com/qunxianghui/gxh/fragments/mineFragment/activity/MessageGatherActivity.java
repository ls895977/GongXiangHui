package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineMessageGatherFragment;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class MessageGatherActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    private  String[] titles=new String[]{"我的帖子","我的关注","我的粉丝"};
    private List<Fragment>fragments=new ArrayList<>();

    @BindView(R.id.mine_MessageGather_Tablayout_common)
    TabLayout mineMessageGatherTablayoutCommon;
    @BindView(R.id.mine_messageGather_viewpager)
    ViewPager mineMessageGatherViewpager;
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
    protected void initDatas() {
  new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("信息汇总");

  //设置tablayout的点击事件
        mineMessageGatherTablayoutCommon.setOnTabSelectedListener(this);
        fragments.add(new MineMessageGatherFragment());
        fragments.add(new  MineMessageGatherFragment());
        fragments.add(new  MineMessageGatherFragment());
        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mineMessageGatherViewpager.setAdapter(mineTabViewPagerAdapter);
        mineMessageGatherTablayoutCommon.setupWithViewPager(mineMessageGatherViewpager);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
