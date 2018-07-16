package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineMessageFollowFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineMessageFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineMessageSystemFragment;
import com.qunxianghui.gxh.widget.TitleBuilder;

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
    private String[] titles = new String[]{"评论我的", "我的跟帖", "系统消息"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;
    private int index;

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

        index = getIntent().getIntExtra("index", 0);

    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("我的消息");

//设置tablayout的点击事件
        mineMyMessaageTablayoutCommon.setOnTabSelectedListener(this);
        fragments.add(new MineMessageFragment());
        fragments.add(new MineMessageFollowFragment());
        fragments.add(new MineMessageSystemFragment());

        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mineMymessageViewpager.setAdapter(mineTabViewPagerAdapter);
        mineMymessageViewpager.setOffscreenPageLimit(fragments.size());
        mineMyMessaageTablayoutCommon.setupWithViewPager(mineMymessageViewpager);

        if (index!=0){
            mineMymessageViewpager.setCurrentItem(index,false);
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
