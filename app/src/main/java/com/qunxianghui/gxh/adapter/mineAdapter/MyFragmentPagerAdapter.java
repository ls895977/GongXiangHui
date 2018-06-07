package com.qunxianghui.gxh.adapter.mineAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment1;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment2;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment3;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment4;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment5;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"大图通栏", "名片广告", "通栏广告", "二维码广告", "QQ广告 ","贴图广告"};



    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new Fragment2();
        } else if (position == 2) {
            return new Fragment3();
        } else if (position == 3) {
            return new Fragment4();
        } else if (position == 4) {
            return new Fragment5();
        }else if (position==5){
            return new Fragment5();
        }
        return new Fragment1();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
