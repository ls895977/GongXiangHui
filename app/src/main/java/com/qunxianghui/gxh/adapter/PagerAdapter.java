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
    private FragmentManager fragmentManager;
    private final List<Fragment> mFragments = new ArrayList<>();


    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(Fragment fragment ){
        mFragments.add(fragment);
    }


}
