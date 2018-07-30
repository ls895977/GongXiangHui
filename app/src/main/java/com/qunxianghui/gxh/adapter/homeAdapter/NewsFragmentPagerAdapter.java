package com.qunxianghui.gxh.adapter.homeAdapter;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.qunxianghui.gxh.db.ChannelItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：ViewPager适配器
 * <p>
 * Created by Mjj on 2016/11/18.
 */

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private FragmentManager mFm;
    private List<ChannelItem> mTitleList;

    public NewsFragmentPagerAdapter(FragmentManager mFm) {
        super(mFm);
        this.mFm = mFm;
    }

    public NewsFragmentPagerAdapter(FragmentManager mFm, ArrayList<Fragment> fragments, List<ChannelItem> titleList) {
        super(mFm);
        this.mFm = mFm;
        this.mFragments = fragments;
        this.mTitleList = titleList;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        if (this.mFragments != null) {
            FragmentTransaction ft = mFm.beginTransaction();
            for (Fragment f : this.mFragments) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            mFm.executePendingTransactions();
        }
        this.mFragments = fragments;
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position).name;
    }

}
