package com.qunxianghui.gxh.adapter.mineAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qunxianghui.gxh.fragments.mineFragment.fragment.AdverTiseCommenFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment1;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment2;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment3;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment4;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment5;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles ;
    private List<Fragment> fragments;


    public MyFragmentPagerAdapter(FragmentManager fm, String[] mTitles, List<Fragment> fragments) {
        super(fm);
        this.mTitles = mTitles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
       return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
