package com.gongxianghui.adapter.homeAdapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private List<Fragment> fragments;


    public MyViewPagerAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;

    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
}


    @Override
    public CharSequence getPageTitle(int position) {
        Log.e("position",position+"........");
        return titles[position];
    }

    @Override
    public int getCount() {
        return fragments==null?0: fragments.size();
    }
}
