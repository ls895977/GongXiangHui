package com.qunxianghui.gxh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小强
 * @time 2018/5/28  18:07
 * @desc ViewPagers适配器
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public PagerAdapter(FragmentManager fragmentManager, String[] titles){
        super(fragmentManager);
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles == null) {
            return "";
        }
        return mTitles[position];
    }
}
