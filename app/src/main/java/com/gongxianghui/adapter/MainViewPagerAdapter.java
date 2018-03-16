package com.gongxianghui.adapter;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;
/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }
    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position) ;
    }

    @Override
    public int getCount() {
        return mFragments==null?0:mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles==null?"null":mTitles.get(position);
    }
}
