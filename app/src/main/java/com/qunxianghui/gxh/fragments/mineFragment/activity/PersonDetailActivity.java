package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineCollectPostFrament;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineCollectVideoFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineCommonFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonDetailActivity extends BaseActivity {
    @BindView(R.id.iv_person_detail_back)
    ImageView ivPersonDetailBack;
    @BindView(R.id.iv_person_detail_head)
    ImageView ivPersonDetailHead;
    @BindView(R.id.tv_person_detail_name)
    TextView tvPersonDetailName;
    @BindView(R.id.tv_person_detail_setep)
    TextView tvPersonDetailSetep;
    @BindView(R.id.tv_person_detail_attention)
    TextView tvPersonDetailAttention;
    @BindView(R.id.mine_tablayout_person_detail)
    TabLayout mineTablayoutPersonDetail;
    @BindView(R.id.mine_person_detail_viewpager)
    ViewPager minePersonDetailViewpager;
    private String[] titles = new String[]{"资讯", "视频", "帖子"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_detail;
    }

    @Override
    protected void initViews() {
//设置tablayout的一个显示方式
        mineTablayoutPersonDetail.setTabMode(TabLayout.MODE_FIXED);
        for (String tab:titles){
            mineTablayoutPersonDetail.addTab(mineTablayoutPersonDetail.newTab().setText(tab));
        }
    }

    @Override
    protected void initDatas() {
        fragments.add(new MineCommonFragment());
        fragments.add(new MineCollectVideoFragment());
        fragments.add(new MineCollectPostFrament());
        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        minePersonDetailViewpager.setAdapter(mineTabViewPagerAdapter);
        mineTablayoutPersonDetail.setupWithViewPager(minePersonDetailViewpager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
