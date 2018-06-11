package com.qunxianghui.gxh.fragments.generalizeFragment.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.MineMessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MonthFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {
    @BindView(R.id.genera_company_paihangTablayout_common)
    TabLayout generaCompanyPaihangTablayoutCommon;
    @BindView(R.id.genera_campany_paihang_viewpager)
    ViewPager generaCampanyPaihangViewpager;
    Unbinder unbinder;
    private String[] titles = new String[]{"曝光", "点击", "转发","文章"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragement_month_paibang;
    }

    @Override
    public void initDatas() {

//设置tablayout的点击事件
        generaCompanyPaihangTablayoutCommon.setOnTabSelectedListener(this);
        fragments.add(new GeneraLizeMonthSortFragment("view_cnt"));
        fragments.add(new GeneraLizeMonthSortFragment("click_cnt"));
        fragments.add(new GeneraLizeMonthSortFragment("forward_cnt"));
        fragments.add(new GeneraLizeMonthSortFragment("article_cnt"));

        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(mActivity.getSupportFragmentManager(), fragments, titles);
        generaCampanyPaihangViewpager.setAdapter(mineTabViewPagerAdapter);
        generaCompanyPaihangTablayoutCommon.setupWithViewPager(generaCampanyPaihangViewpager);
    }

    @Override
    public void initViews(View view) {
        //设置tablalayout的一个显示方式
        generaCompanyPaihangTablayoutCommon.setTabMode(TabLayout.MODE_FIXED);
        //循环注入
        for (String tab:titles){
            generaCompanyPaihangTablayoutCommon.addTab(generaCompanyPaihangTablayoutCommon.newTab().setText(tab));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
